package io.mvvm.controller;

import io.mvvm.captcha.CaptchaGeneratorFactory;
import io.mvvm.model.CaptchaVO;
import io.mvvm.model.Ret;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: io-admin
 * @description: 验证码
 * @author: 潘南旭
 * @create: 2021-06-12 22:58
 **/
@RestController
@RequestMapping("/common")
public class CaptchaController {

    @GetMapping("/captcha.jpg")
    public Ret<CaptchaVO> captchaGenerator() {
        return Ret.success(CaptchaGeneratorFactory.getInstance().generator());
    }

}
