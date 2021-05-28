package io.mvvm.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program: io-admin
 * @description: 启动器使用式例
 * @author: Mr. Pan
 * @create: 2021-05-28 20:41
 **/
@Slf4j
@Component
public class AccountBootstrap implements IBootstrap {

    @Override
    public int order() {
        return 0;
    }

    @Override
    public void start(String... args) {
        log.info("AccountBootstrap...");
    }

    @Override
    public void shutdown() {
        log.info("AccountShutdown...");
    }
}
