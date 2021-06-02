package io.mvvm.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;

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
    public static final String[] EXCLUDE_URLS = {"/common**"};

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation invocation = (FilterInvocation) object;
        String requestUrl = invocation.getRequestUrl();
        // 如果是公共接口,则返回一个匿名角色
        if (hasPermission(requestUrl, EXCLUDE_URLS)) {
            return SecurityConfig.createList("ROLE_ANONYMOUS");
        }
        //TODO 后面 roles 改为从数据库查询或者redis查询
        String[] roles = {"root"};
        return SecurityConfig.createList(roles);
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
