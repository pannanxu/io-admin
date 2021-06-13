package io.mvvm.constant;

/**
 * @program: io-admin
 * @description: 授权相关常量
 * @author: 潘南旭
 * @create: 2021-06-03 00:56
 **/
public final class SecurityConstant {

    public static final String CONTENT_TYPE_JSON_UTF8 = "application/json; charset=utf-8";

    public static final String AJAX_LOGIN_URI = "/ajax/login";
    public static final String[] EXCLUDE_URI = {AJAX_LOGIN_URI, "/common/**", "/favicon.ico"};

    /**
     * 当无权限的时候，返回此角色，让无权限处理器处理
     */
    public static final String ROLE_NULL = "ROLE_NULL";
    /**
     * 未认证用户的角色
     */
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

}
