package io.mvvm.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Set;

/**
 * @program: io-admin
 * @description: 用户账户信息
 * @author: Mr. Pan
 * @create: 2021-06-01 19:22
 **/
@lombok.Getter
@lombok.Setter
public class UserAccountDetails implements UserDetails, Serializable {

    private Long id;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 角色
     */
    private Set<GrantedAuthority> authorities;
    /**
     * 是否过期
     */
    private boolean accountNonExpired;
    /**
     * 是否锁定
     */
    private boolean accountNonLocked;
    /**
     * 凭证是否过期
     */
    private boolean credentialsNonExpired;
    /**
     * 是否启用
     */
    private boolean enabled;

}
