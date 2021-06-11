package io.mvvm.handler;

import io.mvvm.constant.SecurityConstant;
import io.mvvm.enums.MethodEnum;
import io.mvvm.mapper.IUserAccountMapper;
import io.mvvm.model.ResourceApiDO;
import io.mvvm.model.UserAccountDetails;
import io.mvvm.utils.WebSecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: io-admin
 * @description: 从数据库中获取url对应的role信息
 * @author: Mr. Pan
 * @create: 2021-06-02 20:23
 **/
@Slf4j
@Component
public class UrlRolesFilterHandler implements FilterInvocationSecurityMetadataSource {

    /**
     * 不需要授权的uri
     */
    public static final String[] EXCLUDE_URLS = {"/common/**"};

    @Resource
    private IUserAccountMapper iUserAccountMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation invocation = (FilterInvocation) object;
        HttpServletRequest request = invocation.getRequest();
        String requestUrl = invocation.getRequestUrl();
        String method = request.getMethod();

        Authentication authentication = WebSecurityContextHolder.getAuthentication();
        Object principal = authentication.getPrincipal();
        // 如果未登录，则验证是否为公共接口
        if (!(principal instanceof UserAccountDetails)) {
            String role = hasPermission(requestUrl, EXCLUDE_URLS) ?
                    SecurityConstant.ROLE_ANONYMOUS :
                    SecurityConstant.ROLE_NULL;
            return SecurityConfig.createList(role);
        }

        return iUserAccountMapper.selectRoleNamesByResourceUri(requestUrl, MethodEnum.getType(method))
                .stream()
                .map(SecurityConfig::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public boolean hasPermission(String requestUrl, String[] urls) {
        // 路径匹配器
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String url : urls) {
            if (antPathMatcher.match(url, requestUrl)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 按照ant风格的uri做路由权限验证
     * @param method        请求方式
     * @param requestUrl    请求URI
     * @param roleNames     用户角色列表
     * @return              ConfigAttribute
     */
    private List<ConfigAttribute> byAntCreateConfigAttributes(String method, String requestUrl, Set<String> roleNames) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        List<ConfigAttribute> roles = iUserAccountMapper.selectResourceApiByRoleName(MethodEnum.getType(method), roleNames)
                .stream()
                .filter(e -> antPathMatcher.match(e.getUri(), requestUrl))
                .map(ResourceApiDO::getRoleName)
                .map(SecurityConfig::new)
                .collect(Collectors.toList());
        return roles.isEmpty() ? SecurityConfig.createList(SecurityConstant.ROLE_NULL) : roles;
    }
}
