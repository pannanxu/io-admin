package io.mvvm.captcha;

import io.mvvm.model.CaptchaVO;

/**
 * @program: io-admin
 * @description: 验证码操作的抽象接口
 * @author: Mr. Pan
 * @create: 2021-06-13 18:22
 **/
public interface ICaptchaGenerator {

    /**
     * 生成验证码
     *
     * @return BASE64
     */
    CaptchaVO generator();

    /**
     * 检查验证码
     *
     * @param key  KEY
     * @param code 验证码
     * @return 成功: true, 失败: false
     */
    boolean checkCaptcha(String key, String code);

    /**
     * 删除验证码
     *
     * @param key KEY
     * @return 成功: true, 失败: false
     */
    boolean removeCaptcha(String key);

    /**
     * 写入KEY到缓存中
     * @param key   KEY
     * @param value VALUE
     * @return      Boolean
     */
    boolean setValue(String key, String value);

}
