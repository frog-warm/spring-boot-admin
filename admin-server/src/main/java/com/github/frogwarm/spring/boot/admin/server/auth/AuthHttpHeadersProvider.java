package com.github.frogwarm.spring.boot.admin.server.auth;

import com.github.frogwarm.spring.boot.admin.common.AuthConstants;
import com.github.frogwarm.spring.boot.admin.common.TokenUtil;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.web.client.HttpHeadersProvider;
import org.springframework.http.HttpHeaders;

import java.util.Map;
import java.util.UUID;

import static com.github.frogwarm.spring.boot.admin.common.AuthConstants.AUTH_METADATA_SECRET_NAME;

/**
 * 添加用户信息请求头
 * @author tuzy
 */
public class AuthHttpHeadersProvider implements HttpHeadersProvider {


    @Override
    public HttpHeaders getHeaders(Instance instance) {
        HttpHeaders headers = new HttpHeaders();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
        String token = TokenUtil.get(getSecret(instance), timestamp, nonceStr);
        headers.add(AuthConstants.AUTH_HEADER_TOKEN_NAME, token);
        headers.add(AuthConstants.AUTH_HEADER_TIMESTAMP_NAME, timestamp);
        headers.add(AuthConstants.AUTH_HEADER_NONCE_NAME, nonceStr);
        return headers;
    }


    private static String getSecret(Instance instance) {
        Map<String, String> metadata = instance.getRegistration().getMetadata();
        return metadata.get(AUTH_METADATA_SECRET_NAME);
    }
}
