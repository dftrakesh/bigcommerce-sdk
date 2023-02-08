package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.catalog.brand.Brand;
import com.dft.bigcommerce.model.catalog.brand.BrandRequest;
import com.dft.bigcommerce.model.catalog.brand.BrandWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceBrands extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceBrands(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public Brand createBrand(BrandRequest brandRequest) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "catalog/brands");

        String jsonBody = objectMapper.writeValueAsString(brandRequest);
        HttpRequest request = post(uriBuilder, jsonBody);
        HttpResponse.BodyHandler<BrandWrapper> handler = new JsonBodyHandler<>(BrandWrapper.class);
        BrandWrapper brandWrapper = getRequestWrapped(request, handler);
        return brandWrapper.getData();
    }

    @SneakyThrows
    public Brand getBrand(Integer brandId) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/catalog/brands/" + brandId);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<BrandWrapper> handler = new JsonBodyHandler<>(BrandWrapper.class);
        BrandWrapper brandWrapper = getRequestWrapped(request, handler);
        return brandWrapper.getData();
    }

    @SneakyThrows
    public Brand updateBrand(Integer brandId, BrandRequest brandRequest) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/catalog/brands/" + brandId);

        String jsonBody = objectMapper.writeValueAsString(brandRequest);
        HttpRequest request = put(uriBuilder, jsonBody);
        HttpResponse.BodyHandler<BrandWrapper> handler = new JsonBodyHandler<>(BrandWrapper.class);
        BrandWrapper brandWrapper = getRequestWrapped(request, handler);
        return brandWrapper.getData();
    }
}
