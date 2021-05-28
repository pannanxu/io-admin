package io.mvvm.common.controller;

import io.mvvm.utils.ServletUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: io-admin
 * @description: BaseController
 * @author: Mr. Pan
 * @create: 2021-05-28 19:10
 **/
@Slf4j
public class BaseController {

    protected HttpServletRequest getRequest() {
        return ServletUtil.getRequest();
    }

    protected HttpServletResponse getResponse() {
        return ServletUtil.getResponse();
    }

    protected String getParam(String name) {
        return ServletUtil.getParameter(name);
    }

    protected Integer getParamToInt(String name) {
        return ServletUtil.getParamToInt(name);
    }

}
