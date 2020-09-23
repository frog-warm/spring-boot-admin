package com.github.frogwarm.spring.boot.admin.client;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthVerificationFilter implements Filter {
    private final AuthVerification authVerification;

    public AuthVerificationFilter(AuthVerification authVerification) {
        this.authVerification = authVerification;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (authVerification.verification((HttpServletRequest) servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletResponse.getWriter().println("error");
        }
    }


}
