package io.mvvm.controller;

import io.mvvm.captcha.impl.DefaultCaptchaGenerator;
import io.mvvm.model.CaptchaVO;
import io.mvvm.model.Ret;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: io-admin
 * @description: 验证码
 * @author: Mr. Pan
 * @create: 2021-06-12 22:58
 **/
@RestController
@RequestMapping("/common")
public class CaptchaController {

    @Resource
    private DefaultCaptchaGenerator defaultCaptchaGenerator;

    @GetMapping("/captcha.jpg")
    public Ret<CaptchaVO> captchaGenerator() {
        return Ret.success(defaultCaptchaGenerator.generator());
    }

}
