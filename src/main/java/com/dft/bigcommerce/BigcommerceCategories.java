package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.product.category.Category;
import com.dft.bigcommerce.model.product.category.CategoryRequest;
import com.dft.bigcommerce.model.product.category.CategoryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceCategories extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceCategories(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public Category createCategory(CategoryRequest categoryRequest) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "catalog/categories");

        String jsonBody = objectMapper.writeValueAsString(categoryRequest);
        HttpRequest request = post(uriBuilder, jsonBody);
        HttpResponse.BodyHandler<CategoryWrapper> handler = new JsonBodyHandler<>(CategoryWrapper.class);
        CategoryWrapper categoryWrapper = getRequestWrapped(request, handler);
        return categoryWrapper.getData();
    }
}
