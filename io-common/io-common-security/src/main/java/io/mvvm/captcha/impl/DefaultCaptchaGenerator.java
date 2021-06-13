package io.mvvm.captcha.impl;

import com.wf.captcha.SpecCaptcha;
import io.mvvm.captcha.AbstractCaptchaGenerator;
import io.mvvm.model.CaptchaVO;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @program: io-admin
 * @description: 默认验证码生成
 * @author: Mr. Pan
 * @create: 2021-06-12 21:34
 **/
@Component("defaultCaptchaGenerator")
public class DefaultCaptchaGenerator extends AbstractCaptchaGenerator {

    @Override
    public CaptchaVO abstractGenerator() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        boolean result = setValue(key, verCode);
        CaptchaVO vo = new CaptchaVO();
        if (result) {
            vo.setKey(key);
            vo.setImage(specCaptcha.toBase64());
        }
        return vo;
    }

}
