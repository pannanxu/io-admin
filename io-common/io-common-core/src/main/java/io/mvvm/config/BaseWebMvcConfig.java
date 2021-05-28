package io.mvvm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: io-common-core
 * @description: 提供公共的MVC配置
 * web服务需要继承此类并创建Bean，即直接在类上加入注解{@link org.springframework.context.annotation.Configuration}
 * 如果需要定制化操作，直接重写 {@link WebMvcConfigurer} 此类中的方法即可
 * @author: Mr. Pan
 * @create: 2021-05-22 02:37
 **/
@Slf4j
public class BaseWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    }
}
