package com.github.frogwarm.spring.boot.admin.server.notify;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 钉钉通知
 *
 * @author tuzy
 */

public class DingTalkNotifier extends AbstractStatusChangeNotifier {
    private static final String DEFAULT_MESSAGE =
            "#{registration.name}-#{id} *(#{registration.serviceUrl})* is **#{statusInfo.status}**";
    private static final String DEFAULT_TITLE = "服务告警";
    private final SpelExpressionParser parser = new SpelExpressionParser();
    private RestTemplate restTemplate = new RestTemplate();
    private String webHookToken;
    private String atMobiles;
    private String msgType = "markdown";
    private Expression title;
    private Expression message;

    public DingTalkNotifier(InstanceRepository repository) {
        super(repository);
        this.message = this.parser.parseExpression(DEFAULT_MESSAGE, ParserContext.TEMPLATE_EXPRESSION);
    }

    @Override
    @NonNull
    protected Mono<Void> doNotify(@NonNull InstanceEvent event, @NonNull Instance instance) {
        return Mono.fromRunnable(() -> restTemplate.postForEntity(webHookToken, createMessage(instance), Void.class));
    }


    private HttpEntity<Map<String, Object>> createMessage(Instance instance) {
        Map<String, Object> messageJson = new HashMap<>();
        HashMap<String, String> params = new HashMap<>();
        params.put("text", this.getMessage(instance));
        params.put("title", this.getTitle(instance));
        messageJson.put("atMobiles", instance.getRegistration().getMetadata().getOrDefault("atMobiles", this.atMobiles));
        messageJson.put("msgtype", this.msgType);
        messageJson.put(this.msgType, params);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(messageJson, headers);
    }


    private String getMessage(Instance instance) {
        return this.message.getValue(new StandardEvaluationContext(instance), String.class);
    }

    private String getTitle(Instance instance) {
        if (this.title == null) {
            return DEFAULT_TITLE;
        }
        return this.title.getValue(new StandardEvaluationContext(instance), String.class);
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWebHookToken() {
        return webHookToken;
    }

    public void setWebHookToken(String webHookToken) {
        this.webHookToken = webHookToken;
    }

    public String getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(String atMobiles) {
        this.atMobiles = atMobiles;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Expression getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = this.parser.parseExpression(message, ParserContext.TEMPLATE_EXPRESSION);
    }

    public Expression getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = this.parser.parseExpression(title, ParserContext.TEMPLATE_EXPRESSION);
    }


}
