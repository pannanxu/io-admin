package io.mvvm.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: mvvm-admin
 * @description: Spring上下文工具
 * @author: 潘南旭
 * @create: 2021-05-22 11:32
 **/
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext ctx;

    /**
     * 获取上下文对象
     *
     * @return {@link ApplicationContext}
     */
    public static ApplicationContext getCtx() {
        return ctx;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    /**
     * 根据Bean名称获取Bean对象
     *
     * @param beanName Bean名称
     * @return Object
     */
    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }

    /**
     * 根据Bean类型获取Bean对象
     *
     * @param clazz Bean Type
     * @param <T>   T.class
     * @return T
     */
    public static <T> T getBean(Class<T> clazz) {
        return ctx.getBean(clazz);
    }

    /**
     * 根据Bean名称获取Bean对象
     *
     * @param beanName Bean名称
     * @param clazz    T.class
     * @param <T>      T.class
     * @return T
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return ctx.getBean(beanName, clazz);
    }

    /**
     * 上下文是否存在Bean
     *
     * @param beanName Bean名称
     * @return true or false
     */
    public static boolean contains(String beanName) {
        return ctx.containsBean(beanName);
    }

    /**
     * 获取对应Bean名称的类型
     *
     * @param beanName Bean名称
     * @return 返回对应的Bean类型
     */
    public static Class<?> getType(String beanName) {
        return ctx.getType(beanName);
    }

    /**
     * 根据指定类型获取其实现的名称
     *
     * @return 实现的名称
     */
    public static <T> String[] getBeansByType(Class<T> clazz) {
        return ctx.getBeanNamesForType(clazz);
    }

    /**
     * 获取指定接口的所有实现
     *
     * @param clazz T.class
     * @param <T>   T
     * @return Set<T>
     */
    public static <T> Set<T> getBeanImplByType(Class<T> clazz) {
        return Arrays.stream(getBeansByType(clazz))
                .map(e -> getBean(e, clazz))
                .collect(Collectors.toSet());
    }

    /**
     * 获取标注指定注解的bean
     * @param clazzAnnotation 注解类型
     * @param <A>             <A extends Annotation>
     * @return                beans
     */
    public static <A extends Annotation> Map<String, Object> getBeanByAnnotation(Class<A> clazzAnnotation) {
        return ctx.getBeansWithAnnotation(clazzAnnotation);
    }

}
