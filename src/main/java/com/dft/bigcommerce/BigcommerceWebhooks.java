package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.credentials.BigcommerceCredentials;
import com.dft.bigcommerce.model.webhooks.WebhookRequest;
import com.dft.bigcommerce.model.webhooks.WebhookWrapper;
import com.dft.bigcommerce.model.webhooks.WebhooksWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class BigcommerceWebhooks extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String HOOKS_ENDPOINT = "/hooks";
    private static final String FORWARD_SLASH_CHARACTER = "/";

    public BigcommerceWebhooks(BigcommerceCredentials credentials) {
        super(credentials);
    }

    @SneakyThrows
    public WebhookWrapper createWebhook(WebhookRequest webhookRequest) {
        URI uri = baseUrl(HOOKS_ENDPOINT);

        String jsonBody = objectMapper.writeValueAsString(webhookRequest);
        HttpRequest request = post(uri, jsonBody);
        HttpResponse.BodyHandler<WebhookWrapper> handler = new JsonBodyHandler<>(WebhookWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public WebhooksWrapper getAllWebhooks(HashMap<String, String> params){
        URI uri = baseUrl(HOOKS_ENDPOINT);
        uri = addParameters(uri, params);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<WebhooksWrapper> handler = new JsonBodyHandler<>(WebhooksWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public WebhookWrapper getWebhookById(Integer webhookId){
        URI uri = baseUrl(HOOKS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(webhookId.toString()));

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<WebhookWrapper> handler = new JsonBodyHandler<>(WebhookWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public WebhookWrapper updateWebhook(Integer webhookId, WebhookRequest webhookRequest){
        URI uri = baseUrl(HOOKS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(webhookId.toString()));

        String jsonBody = objectMapper.writeValueAsString(webhookRequest);
        HttpRequest request = put(uri, jsonBody);
        HttpResponse.BodyHandler<WebhookWrapper> handler = new JsonBodyHandler<>(WebhookWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public void deleteWebhook(Integer webhookId){
        URI uri = baseUrl(HOOKS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(webhookId.toString()));

        HttpRequest request = delete(uri);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }
}