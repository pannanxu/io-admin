package io.mvvm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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
        registry.addMapping("/**")
                //必须字段，允许跨域的域名，可以用*表示允许任何域名使用
                .allowedOrigins("http://127.0.0.1")
                //可选字段，允许跨域的方法，使用*表示允许任何方法
                .allowedMethods("*")
                //可选字段，布尔值，表示是否允许发送cookie信息
                .allowCredentials(true)
                //允许任何请求头
                .allowedHeaders("*")
                //可选字段，指定响应头里的字段信息
                .exposedHeaders("name")
                //可选字段，用来指定本次预检请求的有效期，单位为秒
                .maxAge(1000)
        ;
    }

    /**
     * 配置请求，响应的消息转换器
     *
     * @param converters 转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                converters.add(0, converter);
                break;
            }
        }
    }

    /**
     * 配置静态资源映射
     *
     * @param registry 注册资源处理器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
