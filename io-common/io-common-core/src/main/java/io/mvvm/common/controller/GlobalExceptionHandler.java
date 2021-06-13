package io.mvvm.common.controller;

import io.mvvm.model.Ret;
import io.mvvm.enums.RetTypeEnum;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: io-admin
 * @description: 全局异常捕获
 * @author: 潘南旭
 * @create: 2021-05-29 10:57
 **/
@RestControllerAdvice(basePackages = "io.mvvm.controller", assignableTypes = BaseController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Ret<Object> exception(Exception e) {
        e.printStackTrace();
        return Ret.err();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Ret<Object> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        String message = objectError.getDefaultMessage();
        return Ret.type(RetTypeEnum.REQUEST_ERROR, null, message);
    }

}
