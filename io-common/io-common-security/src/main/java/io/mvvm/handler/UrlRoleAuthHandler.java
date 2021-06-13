package io.mvvm.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @program: io-admin
 * @description: 访问决定投票 {@link UrlAccessDecisionManager}
 * @author: 潘南旭
 * @create: 2021-06-02 20:13
 **/
@Slf4j
@Component
public class UrlRoleAuthHandler implements AccessDecisionVoter<Object> {

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return null != configAttribute.getAttribute();
    }

    /**
     * ACCESS_GRANTED – 同意
     * ACCESS_DENIED – 拒绝
     * ACCESS_ABSTAIN – 弃权
     */
    @Override
    public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> urlRoles) {
        if (null == authentication) {
            return ACCESS_DENIED;
        }

        // 角色列表
        Collection<? extends GrantedAuthority> userRoles = authentication.getAuthorities();

        /*
         * 遍历链接中对应的权限
         *
         */
        for (ConfigAttribute urlRole : urlRoles) {
            if (this.supports(urlRole)) {
                /*
                 * 此处默认值为弃权，表示只要有一个角色对应上，用户就可以访问链接
                 * 如果值改为拒绝，表示必须全部角色包含才能访问链接
                 */
                // 遍历用户中对应的角色列表
                for (GrantedAuthority userRole : userRoles) {
                    if (urlRole.getAttribute().equals(userRole.getAuthority())) {
                        return ACCESS_GRANTED;
                    }
                }
            }
        }
        return ACCESS_ABSTAIN;
    }

    @Override
    public boolean supports(Class aClass) {
        return true;
    }
}
