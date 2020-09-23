package com.github.frogwarm.spring.boot.admin.client;

import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限验证
 *
 * @author tuzy
 */
public interface AuthVerification {

    boolean verification(HttpServletRequest request);

    boolean verification(ServerHttpRequest request);
}
