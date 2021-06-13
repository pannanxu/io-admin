package io.mvvm.enums;

/**
 * @program: io-admin
 * @description: 响应类型枚举
 * @author: 潘南旭
 * @create: 2021-05-31 18:54
 **/
public enum RetTypeEnum {

    /**
     * 处理失败
     */
    FAIL(-1, "fail"),
    /**
     * 操作成功
     */
    SUCCESS(200, "success"),
    /**
     * 重定向
     */
    REDIRECT(301, "redirect"),
    /**
     * 请求异常：请求方式、参数格式等
     */
    REQUEST_ERROR(400, "参数传递格式错误"),
    /**
     * 未授权
     */
    UNAUTHORIZED(401, "未授权"),
    /**
     * 授权失败
     */
    AUTHORIZED_FAIL(402, "授权失败"),
    /**
     * 无权限
     */
    NO_PERMISSION(403,"无权限"),
    /**
     * 404
     */
    NOT_FOUND(404, "资源未找到"),
    /**
     * 未登录
     */
    NOT_LOGGED_IN(405, "未登录"),
    /**
     * 限流
     */
    LIMITING(429, "操作频繁"),
    /**
     * 系统异常
     */
    ERROR(500, "系统异常"),
    ;

    /**
     * 错误码
     */
    private final int code;
    /**
     * 错误码对应的提示
     */
    private final String msg;

    RetTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
