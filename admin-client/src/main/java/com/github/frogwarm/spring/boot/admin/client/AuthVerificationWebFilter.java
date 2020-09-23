package com.github.frogwarm.spring.boot.admin.client;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class AuthVerificationWebFilter implements WebFilter {
    private final AuthVerification authVerification;

    public AuthVerificationWebFilter(AuthVerification authVerification) {
        this.authVerification = authVerification;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        if (this.authVerification.verification(serverWebExchange.getRequest())) {
            return webFilterChain.filter(serverWebExchange);
        }
        return Mono.empty();
    }


}
