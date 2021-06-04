package io.mvvm.handler;

import com.alibaba.fastjson.JSON;
import io.mvvm.constant.SecurityConstant;
import io.mvvm.model.Ret;
import io.mvvm.enums.RetTypeEnum;
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
 * @author: Mr. Pan
 * @create: 2021-06-02 19:58
 **/
@Slf4j
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        log.warn(e.getMessage());
        httpServletResponse.setContentType(SecurityConstant.CONTENT_TYPE_JSON_UTF8);
        httpServletResponse.getWriter().write(JSON.toJSONString(Ret.type(RetTypeEnum.AUTHORIZED_FAIL)));
    }
}
