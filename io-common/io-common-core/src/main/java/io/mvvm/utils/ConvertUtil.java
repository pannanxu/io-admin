package io.mvvm.utils;

/**
 * @program: io-admin
 * @description: 类型转换工具
 * @author: Mr. Pan
 * @create: 2021-05-28 19:25
 **/
public class ConvertUtil {

    public static final String EMPTY = "";
    public static final Integer ZERO = 0;

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

}
