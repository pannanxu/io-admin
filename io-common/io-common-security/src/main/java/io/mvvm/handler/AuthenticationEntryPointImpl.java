package io.mvvm.handler;

import io.mvvm.constant.SecurityConstant;
import io.mvvm.model.Ret;
import io.mvvm.enums.RetTypeEnum;
import io.mvvm.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: io-admin
 * @description: 匿名用户无权限处理器
 * @author: Mr. Pan
 * @create: 2021-06-02 20:10
 **/
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        log.warn(e.getMessage());
        httpServletResponse.setContentType(SecurityConstant.CONTENT_TYPE_JSON_UTF8);
        httpServletResponse.getWriter().write(JsonUtil.toJsonString(Ret.type(RetTypeEnum.NO_PERMISSION)));
    }
}
