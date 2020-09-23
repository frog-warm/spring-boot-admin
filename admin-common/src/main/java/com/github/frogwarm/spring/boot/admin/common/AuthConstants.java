package com.github.frogwarm.spring.boot.admin.common;

public class AuthConstants {
    // 认证TOKEN名称
    public static final String AUTH_HEADER_TOKEN_NAME = "admin-token";
    // 认证时间戳名称
    public static final String AUTH_HEADER_TIMESTAMP_NAME = "admin-timestamp";
    // 随机字符串
    public static final String AUTH_HEADER_NONCE_NAME = "admin-nonce";
    // 认证秘钥名称
    public static final String AUTH_METADATA_SECRET_NAME = "secret";


    public static final String AUTH_CONFIG_PREFIX = "spring.boot.admin.client.auth";

}
