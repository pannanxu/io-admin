package io.mvvm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: io-admin
 * @description: MyBatisConfig
 * @author: Mr. Pan
 * @create: 2021-05-29 11:19
 **/
@Configuration
@EnableTransactionManagement
@MapperScan({"io.mvvm.mapper"})
public class MyBatisConfig {

}
