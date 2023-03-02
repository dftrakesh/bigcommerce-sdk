package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.option.VariantOptionValueRequest;
import com.dft.bigcommerce.model.option.VariantOptionValueWrapper;
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
    public VariantOptionValueWrapper createVariantOptionValue(VariantOptionValueRequest variantOptionValueRequest, Integer productId, Integer optionId) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "catalog/products/" + productId +"/options/" + optionId + "/values" );

        String jsonBody = objectMapper.writeValueAsString(variantOptionValueRequest);
        HttpRequest request = post(uriBuilder, jsonBody);
        HttpResponse.BodyHandler<VariantOptionValueWrapper> handler = new JsonBodyHandler<>(VariantOptionValueWrapper.class);
        return getRequestWrapped(request, handler);
    }
}