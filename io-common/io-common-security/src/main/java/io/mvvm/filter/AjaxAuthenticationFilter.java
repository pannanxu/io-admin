package io.mvvm.filter;

import com.alibaba.fastjson.JSON;
import io.mvvm.model.UserAccountDetails;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: io-admin
 * @description: AJAX 方式解析用户名和密码
 * @author: Mr. Pan
 * @create: 2021-06-02 20:27
 **/
public class AjaxAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String POST_METHOD = "POST";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 如果不是 POST 登陆，则表示非法请求
        if (!POST_METHOD.equals(request.getMethod())) {
            throw new AuthenticationServiceException("无效请求");
        }
        // 如果不是JSON形式的请求（表单）则使用原有的处理器处理
        if (!request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
            return super.attemptAuthentication(request, response);
        }

        String requestPostStr;
        try {
            requestPostStr = getRequestPostStr(request);
        } catch (IOException e) {
            throw new AuthenticationServiceException("无效请求");
        }

        UserAccountDetails entity = JSON.parseObject(requestPostStr, UserAccountDetails.class);

        String username = entity.getUsername();
        String password = entity.getPassword();

        if (null == username) {
            username = "";
        }
        if (null == password) {
            password = "";
        }

        username = username.trim();
        password = password.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        super.setDetails(request, authRequest);
        return super.getAuthenticationManager().authenticate(authRequest);
    }


    /**
     * 获取 post 请求的 byte[] 数组
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte[] buffer = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int len = request.getInputStream().read(buffer, i, contentLength - i);
            if (len == -1) {
                break;
            }
            i += len;
        }
        return buffer;
    }

    /**
     * 获取 post 请求内容
     */
    public static String getRequestPostStr(HttpServletRequest request) throws IOException {
        byte[] buffer = getRequestPostBytes(request);
        if (null == buffer) {
            return "";
        }
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

}
