package io.mvvm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: io-admin-cli
 * @description: Admin 启动器
 * @author: Mr. Pan
 * @create: 2021-05-23 00:57
 **/
@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
