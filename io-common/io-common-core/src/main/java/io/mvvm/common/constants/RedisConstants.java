package io.mvvm.common.constants;

/**
 * @program: io-admin
 * @description: Redis 常量, 命名规范: 各个模块以":"分割, 结尾也需要, 然后拼接资源的唯一标识(便于查找),
 * 例如: 系统模块的资源表: "system:resource:{id}"
 * @author: 潘南旭
 * @create: 2021-06-13 21:01
 **/
public final class RedisConstants {
    /**
     * 资源所需要的角色
     */
    public static final String RESOURCE_URI_ROLE_API = "system:resource:uri:role:api:";
    public static final String RESOURCE_URI_ROLE_MENU = "system:resource:uri:role:menu:";
    /**
     * 表单登陆验证码
     */
    public static final String CAPTCHA_CODE_STORE_TO_REDIS_KEY = "system:security:login:form:captcha:";

    public static String append(String prefix, String... suffix) {
        StringBuilder key = new StringBuilder(prefix);
        for (String suf : suffix) {
            key.append(suf).append(":");
        }
        key.deleteCharAt(key.lastIndexOf(":"));
        return key.toString();
    }

}
