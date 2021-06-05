package io.mvvm.enums;

import java.util.Arrays;

/**
 * @program: io-admin
 * @description: 请求方式
 * @author: Mr. Pan
 * @create: 2021-06-06 01:24
 **/
public enum MethodEnum {

    /**
     * 0
     */
    GET,
    /**
     * 1
     */
    POST,
    /**
     * 2
     */
    PUT,
    /**
     * 3
     */
    PATCH,
    /**
     * 4
     */
    DELETE,
    /**
     * 5
     */
    COPY,
    /**
     * 6
     */
    HEAD,
    /**
     * 7
     */
    OPTIONS,
    /**
     * 8
     */
    LINK,
    /**
     * 9
     */
    UNLINK,
    /**
     * 10
     */
    PURGE,
    /**
     * 11
     */
    LOCK,
    /**
     * 12
     */
    UNLOCK,
    /**
     * 13
     */
    PROPFIND,
    /**
     * 14
     */
    VIEW
    ;


    public static int getType(String method) {
        MethodEnum methodEnum = MethodEnum.valueOf(method);
        return methodEnum.ordinal();
    }

    public static String getType(int method) {
        return Arrays.stream(MethodEnum.values()).filter(e -> e.ordinal() == method).findFirst().map(Enum::name).orElse(null);
    }

}
