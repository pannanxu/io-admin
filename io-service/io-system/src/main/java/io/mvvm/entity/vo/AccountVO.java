package io.mvvm.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: io-admin
 * @description: AccountVO
 * @author: Mr. Pan
 * @create: 2021-05-25 00:15
 **/
@Getter
@Setter
@ToString
public class AccountVO {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;

}
