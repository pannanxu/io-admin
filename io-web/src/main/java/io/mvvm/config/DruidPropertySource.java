package io.mvvm.config;

import io.mvvm.utils.YamlPropertySourceLoaderUtil;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @program: io-admin
 * @description:
 * @author: Mr. Pan
 * @create: 2021-05-25 18:41
 **/
@Component
@PropertySource(value = "classpath:/config/druid-${spring.profiles.active}.yml", factory = YamlPropertySourceLoaderUtil.class)
public class DruidPropertySource {

}
