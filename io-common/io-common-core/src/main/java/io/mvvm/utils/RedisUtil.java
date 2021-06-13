package io.mvvm.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
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
    private RedisTemplate<String, String> redisTemplateInit;

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
     * 给Key设置过期时间
     * @param key   key
     * @param date  过期时间
     * @return      boolean
     */
    public Boolean expireAt(String key, Date date) {
        try {
            return redisTemplateInit.expireAt(key, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查找key
     * @param pattern   pattern
     * @return          keys
     */
    public Set<String> keys(String pattern) {
        return redisTemplateInit.keys(pattern);
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
        return redisTemplateInit.opsForValue().get(key);
    }

    /**
     * 普通缓存写入
     *
     * @param key   KEY
     * @param value VALUE
     * @return Boolean
     */
    public Boolean set(String key, String value) {
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
    public Boolean set(String key, String value, long time) {
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

    //region List

    /**
     * 根据索引获取list中的值
     * @param key   key
     * @param index 索引
     * @return      value
     */
    public String listIndex(String key, long index) {
        try {
            return redisTemplateInit.boundListOps(key).index(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取全部的list
     * @param key   key
     * @return      val
     */
    public List<String> listRange(String key) {
        return listRange(key, 0, -1);
    }

    /**
     * 获取范围的值列表
     * @param key   key
     * @param start 开始
     * @param end   结束, -1 全部
     * @return      list
     */
    public List<String> listRange(String key, int start, int end) {
        try {
            return redisTemplateInit.boundListOps(key).range(start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 在之前添加值
     * @param key   key
     * @param val   val
     * @return      count
     */
    public Long listPushLeft(String key, String val) {
        try {
            return redisTemplateInit.boundListOps(key).leftPush(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 在之前添加值
     * @param key       key
     * @param values    value
     * @return          count
     */
    public Long listPushLeft(String key, Collection<String> values) {
        try {
            return redisTemplateInit.boundListOps(key).leftPushAll(toArray(values));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }
    //endregion

    private String[] toArray(Collection<String> values) {
        int i = 0;
        String[] val = new String[values.size()];
        for (String value : values) {
            val[i] = value;
            i++;
        }
        return val;
    }


}
