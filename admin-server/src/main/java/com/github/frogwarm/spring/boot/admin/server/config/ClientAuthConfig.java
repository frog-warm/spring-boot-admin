package com.github.frogwarm.spring.boot.admin.server.config;

import com.github.frogwarm.spring.boot.admin.server.auth.AuthHttpHeadersProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.frogwarm.spring.boot.admin.common.AuthConstants.AUTH_CONFIG_PREFIX;

/**
 * 客户端认证header添加配置
 */
@Configuration
public class ClientAuthConfig {

    @Bean
    AuthHttpHeadersProvider AuthHttpHeadersProvider() {
        return new AuthHttpHeadersProvider();
    }
}
