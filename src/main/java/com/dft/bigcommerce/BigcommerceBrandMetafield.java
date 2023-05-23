package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.credentials.BigcommerceCredentials;
import com.dft.bigcommerce.model.product.brandMetafields.BrandMetafieldRequest;
import com.dft.bigcommerce.model.product.brandMetafields.BrandMetafieldWrapper;
import com.dft.bigcommerce.model.product.brandMetafields.BrandMetafieldsWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceBrandMetafield extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceBrandMetafield(BigcommerceCredentials credentials) {
        super(credentials);
    }

    @SneakyThrows
    public BrandMetafieldsWrapper getAllBrandMetafield(Integer brandId) {
        URI uri = baseUrl("/catalog/brands/" + brandId + "/metafields");

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<BrandMetafieldsWrapper> handler = new JsonBodyHandler<>(BrandMetafieldsWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public BrandMetafieldWrapper createBrandMetafield(Integer brandId, BrandMetafieldRequest brandMetafieldRequest) {
        URI uri = baseUrl("/catalog/brands/" + brandId + "/metafields");

        String jsonBody = objectMapper.writeValueAsString(brandMetafieldRequest);
        HttpRequest request = post(uri, jsonBody);
        HttpResponse.BodyHandler<BrandMetafieldWrapper> handler = new JsonBodyHandler<>(BrandMetafieldWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public BrandMetafieldWrapper getBrandMetafieldById(Integer brandId, Integer metafieldId) {
        URI uri = baseUrl("/catalog/brands/" + brandId + "/metafields/" + metafieldId);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<BrandMetafieldWrapper> handler = new JsonBodyHandler<>(BrandMetafieldWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public BrandMetafieldWrapper updateBrandMetafield(Integer brandId, Integer metafieldId, BrandMetafieldRequest brandMetafieldRequest) {
        URI uri = baseUrl("/catalog/brands/" + brandId + "/metafields/" + metafieldId);

        String jsonBody = objectMapper.writeValueAsString(brandMetafieldRequest);
        HttpRequest request = put(uri, jsonBody);
        HttpResponse.BodyHandler<BrandMetafieldWrapper> handler = new JsonBodyHandler<>(BrandMetafieldWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public void deleteBrandMetafield(Integer brandId, Integer metafieldId) {
        URI uri = baseUrl("/catalog/brands/" + brandId + "/metafields/" + metafieldId);

        HttpRequest request = delete(uri);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }
}