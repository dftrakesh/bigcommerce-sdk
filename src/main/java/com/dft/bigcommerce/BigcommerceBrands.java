package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.product.brand.Brand;
import com.dft.bigcommerce.model.product.brand.BrandRequest;
import com.dft.bigcommerce.model.product.brand.BrandWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceBrands extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceBrands(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public Brand createBrand(BrandRequest brandRequest) {
        URI uri = baseUrl("catalog/brands");

        String jsonBody = objectMapper.writeValueAsString(brandRequest);
        HttpRequest request = post(uri, jsonBody);
        HttpResponse.BodyHandler<BrandWrapper> handler = new JsonBodyHandler<>(BrandWrapper.class);
        BrandWrapper brandWrapper = getRequestWrapped(request, handler);
        return brandWrapper.getData();
    }

    @SneakyThrows
    public Brand getBrand(Integer brandId) {
        URI uri = baseUrl("/catalog/brands/" + brandId);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<BrandWrapper> handler = new JsonBodyHandler<>(BrandWrapper.class);
        BrandWrapper brandWrapper = getRequestWrapped(request, handler);
        return brandWrapper.getData();
    }

    @SneakyThrows
    public Brand updateBrand(Integer brandId, BrandRequest brandRequest) {
        URI uri = baseUrl("/catalog/brands/" + brandId);

        String jsonBody = objectMapper.writeValueAsString(brandRequest);
        HttpRequest request = put(uri, jsonBody);
        HttpResponse.BodyHandler<BrandWrapper> handler = new JsonBodyHandler<>(BrandWrapper.class);
        BrandWrapper brandWrapper = getRequestWrapped(request, handler);
        return brandWrapper.getData();
    }
}
