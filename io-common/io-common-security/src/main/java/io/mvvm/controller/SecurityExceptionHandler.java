package io.mvvm.controller;

import io.mvvm.entity.Ret;
import io.mvvm.enums.RetTypeEnum;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: io-admin
 * @description: 权限异常捕获
 * @author: Mr. Pan
 * @create: 2021-06-02 12:43
 **/
@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public Ret<Object> usernameNotFoundException(UsernameNotFoundException e) {
        return Ret.type(RetTypeEnum.AUTHORIZED_FAIL, null, e.getMessage());
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public Ret<Object> authenticationServiceException(AuthenticationServiceException e) {
        return Ret.type(RetTypeEnum.AUTHORIZED_FAIL, null, e.getMessage());
    }

}
