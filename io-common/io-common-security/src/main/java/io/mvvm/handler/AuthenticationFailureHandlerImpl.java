package io.mvvm.handler;

import io.mvvm.constant.SecurityConstant;
import io.mvvm.model.Ret;
import io.mvvm.enums.RetTypeEnum;
import io.mvvm.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: io-admin
 * @description: 授权失败处理器
 * @author: 潘南旭
 * @create: 2021-06-02 19:58
 **/
@Slf4j
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        log.warn(e.getMessage());
        httpServletResponse.setContentType(SecurityConstant.CONTENT_TYPE_JSON_UTF8);
        httpServletResponse.getWriter().write(JsonUtil.toJsonString(Ret.type(RetTypeEnum.AUTHORIZED_FAIL, null, e.getMessage())));
    }
}
