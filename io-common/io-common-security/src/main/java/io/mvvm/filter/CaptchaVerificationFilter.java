package io.mvvm.filter;

import io.mvvm.captcha.impl.DefaultCaptchaGenerator;
import io.mvvm.constant.SecurityConstant;
import io.mvvm.enums.RetTypeEnum;
import io.mvvm.model.Ret;
import io.mvvm.utils.JsonUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: io-admin
 * @description: 验证码认证过滤器
 * @author: 潘南旭
 * @create: 2021-06-13 01:22
 **/
@Component
public class CaptchaVerificationFilter extends OncePerRequestFilter {

    @Resource
    private DefaultCaptchaGenerator defaultCaptchaGenerator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String code = request.getParameter("code");
        String key = request.getParameter("key");
        String uri = request.getRequestURI();

        if (!SecurityConstant.AJAX_LOGIN_URI.equals(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        boolean result = defaultCaptchaGenerator.checkCaptcha(key, code);
        if (!result) {
            response.setContentType(SecurityConstant.CONTENT_TYPE_JSON_UTF8);
            response.getWriter().write(JsonUtil.toJsonString(Ret.type(RetTypeEnum.FAIL, null, "验证码错误")));
            return;
        }

        filterChain.doFilter(request, response);
    }

}
