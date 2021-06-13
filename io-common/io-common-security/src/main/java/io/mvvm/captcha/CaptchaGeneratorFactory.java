package io.mvvm.captcha;

import io.mvvm.utils.SpringContextHolder;

/**
 * @program: io-admin
 * @description: 验证码工厂, 通过此类来得到 继承了 {@link AbstractCaptchaGenerator} 的bean
 * @author: Mr. Pan
 * @create: 2021-06-13 19:01
 **/
public final class CaptchaGeneratorFactory {

    /**
     * 验证码实现类的 bean name
     * TODO 这里为了拓展, 后面可做为动态配置
     */
    private static final String CAPTCHA_GENERATOR_BEAN_NAME = "defaultCaptchaGenerator";

    public static ICaptchaGenerator getInstance() {
        return SpringContextHolder.getBean(CAPTCHA_GENERATOR_BEAN_NAME, ICaptchaGenerator.class);
    }

}
