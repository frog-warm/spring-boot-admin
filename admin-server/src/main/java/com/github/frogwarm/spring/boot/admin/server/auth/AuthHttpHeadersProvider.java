package com.github.frogwarm.spring.boot.admin.server.auth;

import com.github.frogwarm.spring.boot.admin.common.AuthConstants;
import com.github.frogwarm.spring.boot.admin.common.ClientAuthProperties;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.web.client.HttpHeadersProvider;
import org.springframework.http.HttpHeaders;

/**
 * 添加用户信息请求头
 */
public class AuthHttpHeadersProvider implements HttpHeadersProvider {

    private  final ClientAuthProperties clientAuthProperties;

    public AuthHttpHeadersProvider(ClientAuthProperties clientAuthProperties){
        this.clientAuthProperties = clientAuthProperties;
    }

    @Override
    public HttpHeaders getHeaders(Instance instance) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(AuthConstants.AUTH_HEADER_NAME, clientAuthProperties.getToken());
        return headers;
    }
}
