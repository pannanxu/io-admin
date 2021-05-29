package io.mvvm.common.controller;

import io.mvvm.entity.Ret;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: io-admin
 * @description: 全局异常捕获
 * @author: Mr. Pan
 * @create: 2021-05-29 10:57
 **/
@RestControllerAdvice(basePackages = "io.mvvm.controller", assignableTypes = BaseController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Ret exception(Exception e) {
        e.printStackTrace();
        return Ret.err();
    }

}
