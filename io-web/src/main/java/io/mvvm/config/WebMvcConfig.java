package io.mvvm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * @program: io-admin
 * @description: WebMVC配置
 * @author: Mr. Pan
 * @create: 2021-05-23 13:03
 **/
@Configuration
@Slf4j
public class WebMvcConfig extends BaseWebMvcConfig {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("addCorsMappings");
    }
}
