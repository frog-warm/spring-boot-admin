package com.github.frogwarm.spring.boot.admin.client;

import com.github.frogwarm.spring.boot.admin.common.ClientAuthProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.github.frogwarm.spring.boot.admin.common.AuthConstants.AUTH_HEADER_NAME;
import static com.github.frogwarm.spring.boot.admin.common.AuthConstants.AUTH_TOKEN_TYPE;

public class AuthVerificationFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(AuthVerificationFilter.class);
    private final ClientAuthProperties properties;

    public AuthVerificationFilter(ClientAuthProperties properties) {
        properties.check();
        this.properties = properties;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!this.verification(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletResponse.getWriter().println("error");
        }
    }

    private boolean verification(ServletRequest servletRequest) {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        String token = request.getHeader(AUTH_HEADER_NAME);
        if (log.isDebugEnabled()) {
            log.debug("请求地址{}获取到header中token：{}", request.getRequestURI(), token);
        }
        log.debug("请求地址{}获取到header中Authorization：{}", request.getRequestURI(), request.getHeader("Authorization"));
        return AUTH_TOKEN_TYPE.equals(request.getAuthType()) && properties.getToken().equals(token);
    }


}
