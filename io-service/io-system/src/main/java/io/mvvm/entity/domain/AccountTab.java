package io.mvvm.entity.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: io-admin
 * @description: 用户账户模型
 * @author: Mr. Pan
 * @create: 2021-05-23 20:28
 **/
@Getter
@Setter
@Builder
@TableName("SYS_ACCOUNT_TAB")
public class AccountTab {

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
    private int sourceFrom;
    @Version
    @TableField(value = "VERSION")
    private int version;
    @TableField(value = "STATUS")
    private int status;
    @TableField(value = "ACCOUNT_NON_EXPIRED")
    private boolean accountNonExpired;
    @TableField(value = "ACCOUNT_NON_LOCKED")
    private boolean accountNonLocked;
    @TableField(value = "CREDENTIALS_NON_EXPIRED")
    private boolean credentialsNonExpired;
    @TableField(value = "CREATE_TIME")
    private long createTime;
    @TableField(value = "CREATE_IP")
    private String createIp;
    @TableField(value = "LAST_LOGIN_TIME")
    private long lastLoginTime;
    @TableField(value = "LAST_LOGIN_IP")
    private String lastLoginIp;
    @TableField(value = "LOGIN_COUNT")
    private int loginCount;
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "IS_DEL")
    private int isDel;
}
