package io.mvvm.utils;

/**
 * @program: io-admin
 * @description: 类型转换工具
 * @author: 潘南旭
 * @create: 2021-05-28 19:25
 **/
public class ConvertUtil {

    public static final String EMPTY = "";
    public static final Integer ZERO = 0;
    public static final Long ZERO_L = 0L;

    public static String parseString(Object var, String defaultVal) {
        return null == var ? defaultVal : var.toString();
    }

    public static String parseString(Object var) {
        return parseString(var, EMPTY);
    }

    public static Integer parseInteger(Object var, Integer defaultVal) {
        return null == var ? defaultVal : Integer.parseInt(parseString(var));
    }

    public static Integer parseInteger(Object var) {
        return parseInteger(var, ZERO);
    }

    public static Long parseLong(Object var, Long defaultVal) {
        return null == var ? defaultVal : (Long) var;
    }

    public static Long parseLong(Object var) {
        return parseLong(var, ZERO_L);
    }

}
