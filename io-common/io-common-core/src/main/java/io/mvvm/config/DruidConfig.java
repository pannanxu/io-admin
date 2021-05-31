package io.mvvm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.List;

/**
 * @program: io-admin
 * @description: Druid 数据源配置
 * @author: Mr. Pan
 * @create: 2021-05-23 16:37
 **/
@Setter
@Configuration
public class DruidConfig {

    @Value("${spring.config.urlMappings}")
    private String urlMappings;
    @Value("${spring.config.allow}")
    private String allow;
    @Value("${spring.config.deny}")
    private String deny;
    @Value("${spring.config.loginUsername}")
    private String loginUsername;
    @Value("${spring.config.loginPassword}")
    private String loginPassword;
    @Value("${spring.config.resetEnable}")
    private String resetEnable;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean<Servlet> druidServlet() {
        // 进行 druid 监控的配置处理
        ServletRegistrationBean<Servlet> srb = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // 白名单
        srb.addInitParameter("allow", allow);
        // 黑名单
        srb.addInitParameter("deny", deny);
        // 用户名
        srb.addInitParameter("loginUsername", loginUsername);
        // 密码
        srb.addInitParameter("loginPassword", loginPassword);
        // 是否可以重置数据源
        srb.addInitParameter("resetEnable", resetEnable);
        return srb;
    }

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<>();
        frb.setFilter(new WebStatFilter());
        // 所有请求进行监控处理
        frb.addUrlPatterns("/*");
        // 排除名单
        frb.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
        return frb;
    }

}