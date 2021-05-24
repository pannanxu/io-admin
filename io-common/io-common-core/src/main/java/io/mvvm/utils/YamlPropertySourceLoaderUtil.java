package io.mvvm.utils;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: mvvm-admin
 * @description: YAML 配置文件加载工具
 * @author: Mr. Pan
 * @create: 2021-05-22 23:33
 **/
@Component
public class YamlPropertySourceLoaderUtil implements PropertySourceFactory {

    private final YamlPropertySourceLoader loader = new YamlPropertySourceLoader();

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        return loadYaml(resource.getResource());
    }

    private PropertySource<?> loadYaml(Resource path) {
        if (!path.exists()) {
            throw new IllegalArgumentException("Resource " + path + " does not exist");
        }
        try {
            return this.loader.load("custom-resource", path).get(0);
        }
        catch (IOException ex) {
            throw new IllegalStateException("Failed to load yaml configuration from " + path, ex);
        }
    }

}
