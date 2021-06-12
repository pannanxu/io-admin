package io.mvvm.handler;

import io.mvvm.constant.SecurityConstant;
import io.mvvm.model.Ret;
import io.mvvm.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: io-admin
 * @description: 登出处理器
 * @author: Mr. Pan
 * @create: 2021-06-02 20:11
 **/
@Slf4j
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("登出成功");
        response.setContentType(SecurityConstant.CONTENT_TYPE_JSON_UTF8);
        response.getWriter().write(JsonUtil.toJsonString(Ret.success()));
    }
}
