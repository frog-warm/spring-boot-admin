package com.github.frogwarm.spring.boot.admin.client.starter;

import com.github.frogwarm.spring.boot.admin.client.AuthVerification;
import com.github.frogwarm.spring.boot.admin.client.AuthVerificationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;

/**
 * springboot自动配置
 */
@Configuration
@ConditionalOnClass(FilterRegistration.class)
public class AuthVerificationFilterAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(AuthVerificationFilterAutoConfiguration.class);

    /**
     * Filter注册
     *
     * @param authVerification 验证器
     */
    @Bean
    public FilterRegistrationBean<AuthVerificationFilter> authVerificationFilter(AuthVerification authVerification) {
        log.debug("init authVerificationFilter");
        FilterRegistrationBean<AuthVerificationFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new AuthVerificationFilter(authVerification));
        bean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        bean.addUrlPatterns("/actuator", "/actuator/**");
        return bean;
    }
}
