package io.mvvm.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.mvvm.model.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: io-admin
 * @description: 用户账户模型
 * @author: Mr. Pan
 * @create: 2021-05-23 20:28
 **/
@Getter
@Setter
@TableName("SYS_ACCOUNT_TAB")
public class AccountTab extends BaseDomain implements Serializable {

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(value = "USERNAME")
    private String username;
    @TableField(value = "PASSWORD")
    private String password;
    @TableField(value = "EMAIL")
    private String email;
    @TableField(value = "PHONE")
    private String phone;
    @TableField(value = "SOURCE_FROM")
    private Integer sourceFrom;
    @Version
    @TableField(value = "VERSION")
    private Integer version;
    @TableField(value = "STATUS")
    private Integer status;
    @TableField(value = "ACCOUNT_NON_EXPIRED")
    private Boolean accountNonExpired;
    @TableField(value = "ACCOUNT_NON_LOCKED")
    private Boolean accountNonLocked;
    @TableField(value = "CREDENTIALS_NON_EXPIRED")
    private Boolean credentialsNonExpired;
    @TableField(value = "LAST_LOGIN_TIME")
    private Long lastLoginTime;
    @TableField(value = "LAST_LOGIN_IP")
    private String lastLoginIp;
    @TableField(value = "LOGIN_COUNT")
    private Integer loginCount;
    @TableField(value = "CREATE_IP")
    private Long createIp;
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "IS_DEL")
    private Integer isDel;
}
