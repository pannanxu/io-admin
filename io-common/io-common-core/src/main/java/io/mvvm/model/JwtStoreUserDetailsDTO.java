package io.mvvm.model;

import lombok.*;
import java.util.Set;

/**
 * @program: io-admin
 * @description: JWT 中存储的用户信息
 * @author: Mr. Pan
 * @create: 2021-06-07 23:10
 **/
@Data
@ToString
public class JwtStoreUserDetailsDTO {

    private Long id;
    private String username;
    private Set<String> roles;

}
