package io.mvvm.handler;

import io.mvvm.constant.SecurityConstant;
import io.mvvm.model.JwtStoreUserDetailsDTO;
import io.mvvm.model.Ret;
import io.mvvm.utils.JsonUtil;
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
        Object principal = authentication.getPrincipal();
        String token = "";
        if (principal instanceof JwtStoreUserDetailsDTO) {
            JwtStoreUserDetailsDTO user = (JwtStoreUserDetailsDTO) principal;
            token = TokenUtil.generateToken(user);
            log.info("登陆成功");
        }
        httpServletResponse.setContentType(SecurityConstant.CONTENT_TYPE_JSON_UTF8);
        httpServletResponse.getWriter().write(JsonUtil.toJsonString(Ret.success(token)));
    }
}
