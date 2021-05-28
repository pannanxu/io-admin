package io.mvvm.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: io-admin
 * @description: AccountDTO
 * @author: Mr. Pan
 * @create: 2021-05-23 20:59
 **/
@Getter
@Setter
@ToString
public class AccountDTO {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private Integer status;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Long createTime;
    private String createIp;
    private Long lastLoginTime;
    private Long lastLoginIp;
    private Integer loginCount;

}
