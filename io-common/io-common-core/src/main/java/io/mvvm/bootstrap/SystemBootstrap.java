package io.mvvm.bootstrap;

import io.mvvm.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @program: io-admin
 * @description: 启动器
 * 在系统启动的时候，会去扫描 {@link IBootstrap} 接口的所有实现并启动，详情请看接口注释
 * @author: Mr. Pan
 * @create: 2021-05-28 20:23
 **/
@Slf4j
@Component
public class SystemBootstrap implements CommandLineRunner, DisposableBean {

    @Override
    public void run(String... args) {
        log.info("服务器启动...");
        try {
            getBootstrapImpls().forEach(e -> e.start(args));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        log.info("服务器关闭...");
        try {
            getBootstrapImpls().forEach(IBootstrap::shutdown);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Stream<IBootstrap> getBootstrapImpls() {
        return SpringContextHolder.getBeanImplByType(IBootstrap.class)
                .stream()
                .sorted(Comparator.comparingInt(IBootstrap::order).reversed());
    }
}
