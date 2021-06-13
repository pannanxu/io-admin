package io.mvvm.handler;

import io.mvvm.common.constants.RedisConstants;
import io.mvvm.constant.SecurityConstant;
import io.mvvm.model.UserAccountDetails;
import io.mvvm.utils.RedisUtil;
import io.mvvm.utils.WebSecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
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
 * @author: 潘南旭
 * @create: 2021-06-02 20:23
 **/
@Slf4j
@Component
public class UrlRolesFilterHandler implements FilterInvocationSecurityMetadataSource {

    @Resource
    private RedisUtil redisUtil;

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
            boolean hasPermission = hasPermission(requestUrl, SecurityConstant.EXCLUDE_URI);
            if (hasPermission) {
                // 如果是公共uri, 则直接放行
                return null;
            }
            // 如果不是公共Uri, 则直接返回一个空角色, 让决策器去抛出异常
            return SecurityConfig.createList(SecurityConstant.ROLE_NULL);
        }

        String subUri = StringUtils.replace(requestUrl.substring(1), "/", ":");
        String storeKey = RedisConstants.append(RedisConstants.RESOURCE_URI_ROLE, method.toUpperCase(), subUri);
        return redisUtil.listRange(storeKey).stream()
                .map(SecurityConfig::new)
                .collect(Collectors.toList());
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

}
