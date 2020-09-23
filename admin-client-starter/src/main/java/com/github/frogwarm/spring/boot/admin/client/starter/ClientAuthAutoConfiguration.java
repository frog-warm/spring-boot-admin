package com.github.frogwarm.spring.boot.admin.client.starter;

import com.github.frogwarm.spring.boot.admin.client.AuthVerification;
import com.github.frogwarm.spring.boot.admin.client.impl.DefaultAuthVerification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置文件
 *
 * @author tuzy
 */
@Configuration
public class ClientAuthAutoConfiguration {

    @Value("${spring.boot.admin.client.instance.metadata.secret}")
    private String secret;

    /**
     * 配置注入
     */
    @Bean
    @ConditionalOnMissingBean(AuthVerification.class)
    public AuthVerification defaultAuthVerification() {
        return new DefaultAuthVerification(this.secret);
    }


}
