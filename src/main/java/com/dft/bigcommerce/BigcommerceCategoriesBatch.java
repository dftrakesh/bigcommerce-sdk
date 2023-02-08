package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.catalog.categorybatch.Category;
import com.dft.bigcommerce.model.catalog.categorybatch.CategoryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class BigcommerceCategoriesBatch extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceCategoriesBatch(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public CategoryWrapper createCategory(List<Category> categoryList) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "catalog/trees/categories");

        String jsonBody = objectMapper.writeValueAsString(categoryList);
        HttpRequest request = post(uriBuilder, jsonBody);
        HttpResponse.BodyHandler<CategoryWrapper> handler = new JsonBodyHandler<>(CategoryWrapper.class);
        return getRequestWrapped(request, handler);
    }
}