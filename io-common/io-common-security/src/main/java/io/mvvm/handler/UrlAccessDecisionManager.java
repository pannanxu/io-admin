package io.mvvm.handler;

import io.mvvm.constant.SecurityConstant;
import io.mvvm.model.UserAccountDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

/**
 * @program: io-admin
 * @description: 决策管理器, 验证当前用户拥有的角色是否满足当前uri所需要的角色
 * @author: 潘南旭
 * @create: 2021-06-13 00:11
 **/
@Slf4j
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {
    /**
     * 决策管理器
     *
     * @param authentication   认证用户信息
     * @param object           被调用的uri {@link org.springframework.security.web.FilterInvocation}
     * @param configAttributes 被调用的uri所需要的角色，{@link UrlRolesFilterHandler}
     * @throws AccessDeniedException               拒绝访问异常 会被{@link AccessDeniedHandlerImpl} 捕获
     * @throws InsufficientAuthenticationException 身份不足
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (!(object instanceof FilterInvocation)) {
            throw new AccessDeniedException("拒绝访问");
        }
        FilterInvocation invocation = (FilterInvocation) object;
        String uri = invocation.getRequestUrl();
        Object principal = authentication.getPrincipal();
        Set<GrantedAuthority> authorities = null;
        if (principal instanceof UserAccountDetails) {
            UserAccountDetails details = (UserAccountDetails) principal;
            authorities = details.getAuthorities();
        }

        for (ConfigAttribute attribute : configAttributes) {
            if (SecurityConstant.ROLE_NULL.equals(attribute.getAttribute())) {
                throw new AccessDeniedException("无权限");
            }
            if (null == authorities) {
                continue;
            }
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(attribute.getAttribute())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("无权限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
