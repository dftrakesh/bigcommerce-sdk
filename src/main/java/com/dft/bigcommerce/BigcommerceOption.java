package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.option.OptionRequest;
import com.dft.bigcommerce.model.option.OptionWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceOption extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceOption(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public OptionWrapper updateOption(OptionRequest optionRequest, Integer productId, Integer optionId) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "catalog/products/" + productId + "/options/" + optionId);

        String jsonBody = objectMapper.writeValueAsString(optionRequest);
        HttpRequest request = put(uriBuilder, jsonBody);
        HttpResponse.BodyHandler<OptionWrapper> handler = new JsonBodyHandler<>(OptionWrapper.class);
        return getRequestWrapped(request, handler);
    }
}