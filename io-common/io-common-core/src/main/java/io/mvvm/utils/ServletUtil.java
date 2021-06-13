package io.mvvm.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: io-admin
 * @description: Servlet Utils
 * @author: 潘南旭
 * @create: 2021-05-28 19:21
 **/
public class ServletUtil {

    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    public static Integer getParamToInt(String name) {
        return ConvertUtil.parseInteger(getParameter(name));
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) requestAttributes;
    }

}
