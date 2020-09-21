package com.github.frogwarm.spring.boot.admin.common;

public class ClientAuthProperties {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public void check() {
        if (this.token == null) {
            throw new IllegalArgumentException("token不能为空");
        }
    }


}
