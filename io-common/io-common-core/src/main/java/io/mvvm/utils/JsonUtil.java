package io.mvvm.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @program: io-admin
 * @description: JacksonUtil
 * @author: 潘南旭
 * @create: 2021-06-12 21:50
 **/
@Slf4j
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 对象转JSON
     * @param t     对象
     * @param <T>   类型
     * @return      JSON
     */
    public static <T> String toJsonString(T t) {
        try {
            return MAPPER.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            log.warn("对象转JSON异常: [ {} ]", e.getMessage());
        }
        return ConvertUtil.EMPTY;
    }

    /**
     * JSON转对象
     * @param json  JSON
     * @param clazz class
     * @param <T>   类型
     * @return      T
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            log.warn("JSON转对象异常: [ {} ]", e.getMessage());
        }
        return null;
    }

    /**
     * JSON 转集合
     * @param json  JSON
     * @param clazz class
     * @param <T>   类型
     * @return      T
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            return MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            log.warn("JSON转集合异常: [ {} ]", e.getMessage());
        }
        return null;
    }

    /**
     * JSON 转 Map
     * @param json  JSON
     * @param ks    KEY     类型
     * @param vs    VALUE   类型
     * @param <K>   KEY     类型
     * @param <V>   VALUE   类型
     * @return      {@link Map>}
     */
    public static <K, V> Map<K, V> parseMap(String json, Class<K> ks, Class<V> vs) {
        JavaType javaType = MAPPER.getTypeFactory().constructMapType(Map.class, ks, vs);
        try {
            return MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            log.warn("JSON转Map异常: [ {} ]", e.getMessage());
        }
        return null;
    }

}
