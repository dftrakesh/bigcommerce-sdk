package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.product.category.CategoriesWrapper;
import com.dft.bigcommerce.model.product.category.Category;
import com.dft.bigcommerce.model.product.category.CategoryRequest;
import com.dft.bigcommerce.model.product.category.CategoryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class BigcommerceCategories extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String FORWARD_SLASH_CHARACTER = "/";
    private static final String CATALOG_CATEGORIES_ENDPOINT = "/catalog/categories";

    public BigcommerceCategories(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public Category createCategory(CategoryRequest categoryRequest) {
        URI uri = baseUrl(CATALOG_CATEGORIES_ENDPOINT);

        String jsonBody = objectMapper.writeValueAsString(categoryRequest);
        HttpRequest request = post(uri, jsonBody);
        HttpResponse.BodyHandler<CategoryWrapper> handler = new JsonBodyHandler<>(CategoryWrapper.class);
        CategoryWrapper categoryWrapper = getRequestWrapped(request, handler);
        return categoryWrapper.getData();
    }

    @SneakyThrows
    public CategoriesWrapper getAllCategories(HashMap<String, String> params) {
        URI uri = baseUrl(CATALOG_CATEGORIES_ENDPOINT);
        uri = addParameters(uri, params);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<CategoriesWrapper> handler = new JsonBodyHandler<>(CategoriesWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public CategoryWrapper getCategoryById(Integer categoryId, HashMap<String, String> params) {
        URI uri = baseUrl(CATALOG_CATEGORIES_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(categoryId.toString()));
        uri = addParameters(uri, params);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<CategoryWrapper> handler = new JsonBodyHandler<>(CategoryWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public CategoryWrapper updateCategory(Integer categoryId, CategoryRequest categoryRequest) {
        URI uri = baseUrl(CATALOG_CATEGORIES_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(categoryId.toString()));

        String jsonBody = objectMapper.writeValueAsString(categoryRequest);
        HttpRequest request = put(uri, jsonBody);
        HttpResponse.BodyHandler<CategoryWrapper> handler = new JsonBodyHandler<>(CategoryWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public void deleteCategoryById(Integer categoryId) {
        URI uri = baseUrl(CATALOG_CATEGORIES_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(categoryId.toString()));

        HttpRequest request = delete(uri);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    public void deleteAllCategory() {
        URI uri = baseUrl(CATALOG_CATEGORIES_ENDPOINT);

        HttpRequest request = delete(uri);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }
}
