package io.mvvm.model;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: io-admin
 * @description: JWT 中存储的用户信息
 * @author: Mr. Pan
 * @create: 2021-06-07 23:10
 **/
@Builder
@Getter
@Setter
@ToString
public class JwtStoreUserDetailsDTO {

    private Long id;
    private String username;
    private Set<String> roles;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;

}
