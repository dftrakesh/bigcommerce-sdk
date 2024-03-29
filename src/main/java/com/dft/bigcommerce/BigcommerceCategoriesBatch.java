package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.credentials.BigcommerceCredentials;
import com.dft.bigcommerce.model.product.categorybatch.Category;
import com.dft.bigcommerce.model.product.categorybatch.CategoryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class BigcommerceCategoriesBatch extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceCategoriesBatch(BigcommerceCredentials credentials) {
        super(credentials);
    }

    @SneakyThrows
    public CategoryWrapper createCategory(List<Category> categoryList) {
        URI uri = baseUrl("catalog/trees/categories");

        String jsonBody = objectMapper.writeValueAsString(categoryList);
        HttpRequest request = post(uri, jsonBody);
        HttpResponse.BodyHandler<CategoryWrapper> handler = new JsonBodyHandler<>(CategoryWrapper.class);
        return getRequestWrapped(request, handler);
    }
}