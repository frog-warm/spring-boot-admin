package com.github.frogwarm.spring.boot.admin.client.starter;

import com.github.frogwarm.spring.boot.admin.client.AuthVerification;
import com.github.frogwarm.spring.boot.admin.client.AuthVerificationWebFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;

/**
 * springboot自动配置
 */
@Configuration
@ConditionalOnClass(DelegatingWebFluxConfiguration.class)
public class AuthVerificationWebFilterAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(AuthVerificationWebFilterAutoConfiguration.class);

    /**
     * Filter注册
     *
     * @param authVerification 验证器
     */
    @Bean
    public AuthVerificationWebFilter authVerificationWebFilter(AuthVerification authVerification) {
        log.debug("init AuthVerificationWebFilter");
        return new AuthVerificationWebFilter(authVerification);
    }
}
