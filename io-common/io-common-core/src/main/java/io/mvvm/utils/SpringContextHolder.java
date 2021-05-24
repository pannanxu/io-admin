package io.mvvm.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @program: mvvm-admin
 * @description: Spring上下文工具
 * @author: Mr. Pan
 * @create: 2021-05-22 11:32
 **/
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext ctx;

    /**
     * 获取上下文对象
     * @return  {@link ApplicationContext}
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
     * @param beanName Bean名称
     * @return         Object
     */
    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }

    /**
     * 根据Bean类型获取Bean对象
     * @param clazz Bean Type
     * @param <T>   T.class
     * @return      T
     */
    public static <T> T getBean(Class<T> clazz) {
        return ctx.getBean(clazz);
    }

    /**
     * 根据Bean名称获取Bean对象
     * @param beanName  Bean名称
     * @param clazz     T.class
     * @param <T>       T.class
     * @return          T
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return ctx.getBean(beanName, clazz);
    }

    /**
     * 上下文是否存在Bean
     * @param beanName  Bean名称
     * @return          true or false
     */
    public static boolean contains(String beanName) {
        return ctx.containsBean(beanName);
    }

    /**
     * 获取对应Bean名称的类型
     *
     * @param beanName  Bean名称
     * @return          返回对应的Bean类型
     */
    public static Class<?> getType(String beanName) {
        return ctx.getType(beanName);
    }

}
