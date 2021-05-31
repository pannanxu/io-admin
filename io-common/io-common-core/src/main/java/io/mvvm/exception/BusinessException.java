package io.mvvm.exception;

import io.mvvm.enums.RetTypeEnum;

/**
 * @program: io-admin
 * @description: 业务异常
 * @author: Mr. Pan
 * @create: 2021-05-31 20:57
 **/
public class BusinessException extends RuntimeException {

    private String msg;
    private RetTypeEnum type;

    public BusinessException() {
    }

    public BusinessException(String message) {
        this.msg = message;
        this.type = RetTypeEnum.FAIL;
    }

    public BusinessException(RetTypeEnum type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public RetTypeEnum getType() {
        return type;
    }
}
