package io.mvvm.utils;

import io.mvvm.model.JwtStoreUserDetailsDTO;
import io.mvvm.model.UserAccountDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @program: io-common-security
 * @description: Spring Security 上下文工具
 * @author: Mr. Pan
 * @create: 2021-05-22 11:46
 **/
@Component
public class WebSecurityContextHolder {

    /**
     * 获取安全上下文
     * @return  {@link SecurityContext}
     */
    public static SecurityContext getSecurityContext() {
        return SecurityContextHolder.getContext();
    }

    /**
     * 获取当前已认证的主体或认证请求令牌
     * @return  如果没有可用的身份验证信息，则为null
     */
    public static Authentication getAuthentication() {
        return getSecurityContext().getAuthentication();
    }

    /**
     * 设置身份信息
     * @param authentication  身份信息
     */
    public static void setAuthentication(Authentication authentication) {
        getSecurityContext().setAuthentication(authentication);
    }

    public static JwtStoreUserDetailsDTO getUserDetails() {
        Authentication authentication = getAuthentication();
        if (null == authentication) {
            return null;
        }
        UserAccountDetails details = (UserAccountDetails) authentication.getDetails();
        return JwtStoreUserDetailsDTO.builder()
                .username(details.getUsername())
                .accountNonExpired(details.isAccountNonExpired())
                .credentialsNonExpired(details.isCredentialsNonExpired())
                .accountNonLocked(details.isAccountNonLocked())
                .roles(details.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet())).build();
    }

}
