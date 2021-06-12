package io.mvvm.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @program: io-common
 * @description: Redis
 * @author: 潘
 * @create: 2021-05-15 03:10
 **/
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplateInit;

    //region 公共方法

    /**
     * 指定缓存失效时间
     *
     * @param key  key
     * @param time 时间（秒）
     * @return Boolean
     */
    public Boolean expire(String key, long time) {
        try {
            if (time > 0) {
                return redisTemplateInit.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据Key 获取过期时间
     *
     * @param key key
     * @return 时间（秒），0表示不过期
     */
    public Long getExpire(String key) {
        return redisTemplateInit.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key KEY
     * @return Boolean
     */
    public Boolean hasKey(String key) {
        try {
            return redisTemplateInit.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除缓存
     *
     * @param key KEY
     * @return Boolean
     */
    public Boolean del(String... key) {
        if (null != key && key.length > 0) {
            if (key.length == 1) {
                return redisTemplateInit.delete(key[0]);
            }
            Long len = redisTemplateInit.delete(Arrays.asList(key));
            return null != len && len > 0;
        }
        return false;
    }

    //endregion
    //region String

    /**
     * 普通缓存获取
     *
     * @param key KEY
     * @return String Value
     */
    public String get(String key) {
        return key == null ? ConvertUtil.EMPTY : ConvertUtil.parseString(redisTemplateInit.opsForValue().get(key));
    }

    /**
     * 普通缓存写入
     *
     * @param key   KEY
     * @param value VALUE
     * @return Boolean
     */
    public Boolean set(String key, Object value) {
        try {
            redisTemplateInit.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 普通缓存写入并设置时间
     *
     * @param key   KEY
     * @param value VALUE
     * @param time  超时时间
     * @return Boolean
     */
    public Boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplateInit.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                return set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //endregion


}
