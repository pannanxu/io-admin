package io.mvvm.handler;

import com.alibaba.fastjson.JSON;
import io.mvvm.constant.SecurityConstant;
import io.mvvm.entity.Ret;
import io.mvvm.entity.UserAccountDetails;
import io.mvvm.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: io-admin
 * @description: 授权成功处理器
 * @author: Mr. Pan
 * @create: 2021-06-02 19:54
 **/
@Slf4j
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        UserAccountDetails user = (UserAccountDetails) authentication.getPrincipal();
        String token = TokenUtil.generateToken(user.getUsername());
        log.info("授权成功");
        httpServletResponse.setContentType(SecurityConstant.CONTENT_TYPE_JSON_UTF8);
        httpServletResponse.getWriter().write(JSON.toJSONString(Ret.success(token)));
    }
}
