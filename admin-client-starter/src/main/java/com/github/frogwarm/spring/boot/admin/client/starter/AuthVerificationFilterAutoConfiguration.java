package com.github.frogwarm.spring.boot.admin.client.starter;

import com.github.frogwarm.spring.boot.admin.client.AuthVerificationFilter;
import com.github.frogwarm.spring.boot.admin.common.ClientAuthProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.frogwarm.spring.boot.admin.common.AuthConstants.AUTH_CONFIG_PREFIX;

/**
 * springboot自动配置
 */
@Configuration
public class AuthVerificationFilterAutoConfiguration {
    private static Logger log = LoggerFactory.getLogger(AuthVerificationFilterAutoConfiguration.class);

    /**
     * 配置注入
     */
    @Bean
    @ConfigurationProperties(prefix = AUTH_CONFIG_PREFIX)
    ClientAuthProperties clientAuthProperties() {
        return new ClientAuthProperties();
    }

    /**
     * Filter注册
     *
     * @param properties 配置
     */
    @Bean
    public FilterRegistrationBean<AuthVerificationFilter> authVerificationFilter(ClientAuthProperties properties) {
        properties.check();
        log.debug("init authVerificationFilter");
        FilterRegistrationBean<AuthVerificationFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new AuthVerificationFilter(properties));
        bean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        return bean;
    }
}
