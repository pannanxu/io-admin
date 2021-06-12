package io.mvvm.config;

import io.mvvm.constant.SecurityConstant;
import io.mvvm.filter.AjaxAuthenticationFilter;
import io.mvvm.filter.CaptchaVerificationFilter;
import io.mvvm.filter.JwtAuthenticationTokenFilter;
import io.mvvm.handler.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @program: io-admin
 * @description: Spring Security 配置类
 * @author: Mr. Pan
 * @create: 2021-06-02 19:43
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private AuthenticationEntryPointImpl authenticationEntryPoint;
    @Resource
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandlerImpl authenticationFailureHandler;
    @Resource
    private LogoutSuccessHandlerImpl logoutSuccessHandler;
    @Resource
    private AccessDeniedHandlerImpl accessDeniedHandler;
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Resource
    private UrlRolesFilterHandler urlRolesFilterHandler;
    // @Resource
    // private UrlRoleAuthHandler urlRoleAuthHandler;
    @Resource
    private AuthenticationProviderImpl authenticationProvider;
    @Resource
    private UrlAccessDecisionManager urlAccessDecisionManager;
    @Resource
    private CaptchaVerificationFilter captchaVerificationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        // 自定义的安全认证
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /* 开启跨域共享，  跨域伪造请求限制=无效 */
        http.cors().and().csrf().disable();
        /* 无状态 无session */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /* 无需认证的请求路径 */
        // http.authorizeRequests().antMatchers(SecurityConstant.EXCLUDE_URI).permitAll();
        /* 动态url权限 */
        http.authorizeRequests().withObjectPostProcessor(new DefinedObjectPostProcessor());
        /* url决策 */
        // http.authorizeRequests().accessDecisionManager(accessDecisionManager());
        /* 开启授权认证 */
        http.authorizeRequests().anyRequest().authenticated();

        /* 登录过期/未登录 处理 */
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        /* 权限不足(没有赋予角色) 处理 */
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        /* 验证码过滤器，在获取用户名和密码前面执行 */
        http.addFilterBefore(captchaVerificationFilter, AjaxAuthenticationFilter.class);
        /* 自定义登陆表单解析器 */
        http.addFilterAt(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        /* 配置token验证过滤器 */
        http.addFilterBefore(jwtAuthenticationTokenFilter, AjaxAuthenticationFilter.class);

        /* 登出 */
        http.logout().logoutSuccessHandler(logoutSuccessHandler);
    }

    /**
     * 自定义JSON登陆参数解析
     */
    @Bean
    public AjaxAuthenticationFilter ajaxAuthenticationFilter() throws Exception {
        AjaxAuthenticationFilter filter = new AjaxAuthenticationFilter();
        filter.setAuthenticationManager(super.authenticationManagerBean());
        filter.setFilterProcessesUrl(SecurityConstant.AJAX_LOGIN_URI);
        // 登陆成功处理器
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        // 登陆失败处理器
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return filter;
    }

    /**
     * AffirmativeBased – 任何一个AccessDecisionVoter返回同意则允许访问
     * ConsensusBased – 同意投票多于拒绝投票（忽略弃权回答）则允许访问
     * UnanimousBased – 每个投票者选择弃权或同意则允许访问
     *
     * 决策管理
     */
    /*private AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();
        decisionVoters.add(new WebExpressionVoter());
        decisionVoters.add(new AuthenticatedVoter());
        decisionVoters.add(new RoleVoter());
        *//* 路由权限管理 *//*
        decisionVoters.add(urlRoleAuthHandler);
        return new UnanimousBased(decisionVoters);
    }*/

    class DefinedObjectPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {
        @Override
        public <O extends FilterSecurityInterceptor> O postProcess(O object) {
            // 配置安全元数据
            object.setSecurityMetadataSource(urlRolesFilterHandler);
            // 配置url权限的决策器
            object.setAccessDecisionManager(urlAccessDecisionManager);
            return object;
        }
    }

    /**
     * 密码加密方式
     * @return {@link BCryptPasswordEncoder}
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
