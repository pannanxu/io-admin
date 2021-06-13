package io.mvvm.captcha;

import io.mvvm.common.constants.RedisConstants;
import io.mvvm.model.CaptchaVO;
import io.mvvm.utils.RedisUtil;

import javax.annotation.Resource;

/**
 * @program: io-admin
 * @description: 验证码生成器, 更多验证码的实现继承此类, 并重写 {{@link #abstractGenerator()} ()}} 方法
 * 然后在工厂方法中 {@link CaptchaGeneratorFactory} 修改bean名称
 * @author: 潘南旭
 * @create: 2021-06-12 21:30
 **/
public abstract class AbstractCaptchaGenerator implements ICaptchaGenerator {

    /**
     * 验证码在Redis中存储的位置
     */
    public static final String CAPTCHA_CODE_STORE_TO_REDIS_KEY = RedisConstants.CAPTCHA_CODE_STORE_TO_REDIS_KEY;

    @Resource
    protected RedisUtil redisUtil;

    /**
     * 生成验证码,子类实现此方法后自定义验证码生成的实现
     *
     * @return BASE64
     */
    protected abstract CaptchaVO abstractGenerator();

    /**
     * 生成验证码 {@link #abstractGenerator()}
     *
     * @return BASE64
     */
    @Override
    public CaptchaVO generator() {
        return abstractGenerator();
    }

    /**
     * 检查验证码
     *
     * @param key  KEY
     * @param code 验证码
     * @return 成功: true, 失败: false
     */
    @Override
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
    @Override
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
    @Override
    public boolean setValue(String key, String value) {
        return redisUtil.set(CAPTCHA_CODE_STORE_TO_REDIS_KEY + key, value, 1000 * 5L);
    }
}
