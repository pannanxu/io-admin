package io.mvvm.entity.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @program: io-admin
 * @description: AccountDTO
 * @author: Mr. Pan
 * @create: 2021-05-23 20:59
 **/
@lombok.Data
public class AccountDTO {

    private Long id;
    @NotNull(message = "账户名称不能为空")
    @Size(min = 8, max = 16, message = "账户名称为10-16个字符")
    private String username;
    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码为6-20个字符")
    private String password;
    @Email(message = "邮箱格式错误")
    private String email;
    private String phone;

}
