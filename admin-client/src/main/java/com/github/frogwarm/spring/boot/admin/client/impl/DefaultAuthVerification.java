package com.github.frogwarm.spring.boot.admin.client.impl;

import com.github.frogwarm.spring.boot.admin.client.AuthVerification;
import com.github.frogwarm.spring.boot.admin.client.AuthVerificationFilter;
import com.github.frogwarm.spring.boot.admin.common.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.github.frogwarm.spring.boot.admin.common.AuthConstants.AUTH_HEADER_NONCE_NAME;
import static com.github.frogwarm.spring.boot.admin.common.AuthConstants.AUTH_HEADER_TIMESTAMP_NAME;
import static com.github.frogwarm.spring.boot.admin.common.AuthConstants.AUTH_HEADER_TOKEN_NAME;

/**
 * 权限校验实现
 *
 * @author tuzy
 */
public class DefaultAuthVerification implements AuthVerification {
    private static final Logger log = LoggerFactory.getLogger(AuthVerificationFilter.class);
    private final String secret;

    public DefaultAuthVerification(String secret) {
        this.secret = secret;
    }

    public boolean verification(HttpServletRequest request) {
        String token = request.getHeader(AUTH_HEADER_TOKEN_NAME);
        if (log.isDebugEnabled()) {
            log.debug("请求地址{}获取到header中token：{}", request.getRequestURI(), token);
        }
        return verification(token, request.getHeader(AUTH_HEADER_TIMESTAMP_NAME), request.getHeader(AUTH_HEADER_NONCE_NAME), request.getRequestURI());
    }

    public boolean verification(ServerHttpRequest request) {
        if (!request.getPath().value().startsWith("/actuator")) {
            return true;
        }
        String token = request.getHeaders().getFirst(AUTH_HEADER_TOKEN_NAME);
        if (log.isDebugEnabled()) {
            log.debug("请求地址{}获取到header中token：{}", request.getPath(), token);
        }
        return verification(token, request.getHeaders().getFirst(AUTH_HEADER_TIMESTAMP_NAME), request.getHeaders().getFirst(AUTH_HEADER_NONCE_NAME), request.getPath().value());
    }

    private boolean verification(String token, String timestamp, String nonceStr, String url) {
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonceStr)) {
            log.warn("{}验证权限失败，token: {}, timestamp: {} ,nonceStr: {}。", url, token, timestamp, nonceStr);
            return false;
        }
        boolean v = TokenUtil.verification(token, this.secret, timestamp, nonceStr);
        if (!v) {
            log.warn("{}验证权限失败，token: {}, timestamp: {} ,nonceStr: {}。", url, token, timestamp, nonceStr);
        }
        return v;
    }


}
