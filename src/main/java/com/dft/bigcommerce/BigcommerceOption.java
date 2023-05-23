package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.credentials.BigcommerceCredentials;
import com.dft.bigcommerce.model.option.OptionRequest;
import com.dft.bigcommerce.model.option.OptionWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceOption extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceOption(BigcommerceCredentials credentials) {
        super(credentials);
    }

    @SneakyThrows
    public OptionWrapper updateOption(OptionRequest optionRequest, Integer productId, Integer optionId) {
        URI uri = baseUrl("catalog/products/" + productId + "/options/" + optionId);

        String jsonBody = objectMapper.writeValueAsString(optionRequest);
        HttpRequest request = put(uri, jsonBody);
        HttpResponse.BodyHandler<OptionWrapper> handler = new JsonBodyHandler<>(OptionWrapper.class);
        return getRequestWrapped(request, handler);
    }
}