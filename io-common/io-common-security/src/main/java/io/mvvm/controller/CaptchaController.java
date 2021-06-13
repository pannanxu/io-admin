package io.mvvm.controller;

import io.mvvm.captcha.CaptchaGeneratorFactory;
import io.mvvm.captcha.ICaptchaGenerator;
import io.mvvm.model.CaptchaVO;
import io.mvvm.model.Ret;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Ret<CaptchaVO> captchaGenerator(@RequestParam(required = false, defaultValue = "") String key) {
        ICaptchaGenerator instance = CaptchaGeneratorFactory.getInstance();
        if (StringUtils.isNotBlank(key)) {
            instance.removeCaptcha(key);
        }
        return Ret.success(instance.generator());
    }

}
