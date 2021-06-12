package io.mvvm.captcha;

import io.mvvm.model.CaptchaVO;
import io.mvvm.utils.RedisUtil;

import javax.annotation.Resource;

/**
 * @program: io-admin
 * @description: 验证码生成器
 * @author: Mr. Pan
 * @create: 2021-06-12 21:30
 **/
public abstract class AbstractCaptchaGenerator {

    public static final String CAPTCHA_CODE_STORE_TO_REDIS_KEY = "captcha:";

    @Resource
    protected RedisUtil redisUtil;

    /**
     * 生成验证码
     *
     * @return BASE64
     */
    public abstract CaptchaVO generator();

    /**
     * 检查验证码
     *
     * @param key  KEY
     * @param code 验证码
     * @return 成功: true, 失败: false
     */
    public boolean checkCaptcha(String key, String code) {
        if (null == key || null == code) {
            return false;
        }
        String redisCode = redisUtil.get(CAPTCHA_CODE_STORE_TO_REDIS_KEY + key);
        boolean result = code.equals(redisCode);
        if (result) {
            removeCaptcha(key);
        }
        return result;
    }

    /**
     * 删除验证码
     *
     * @param key KEY
     * @return 成功: true, 失败: false
     */
    public boolean removeCaptcha(String key) {
        if (null == key) {
            return false;
        }
        return redisUtil.del(CAPTCHA_CODE_STORE_TO_REDIS_KEY + key);
    }

    /**
     * 写入KEY到缓存中
     * @param key   KEY
     * @param value VALUE
     * @return      Boolean
     */
    public boolean setValue(String key, String value) {
        return redisUtil.set(CAPTCHA_CODE_STORE_TO_REDIS_KEY + key, value, 1000 * 5L);
    }
}
