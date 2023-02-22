package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.product.brandMetafields.BrandMetafieldRequest;
import com.dft.bigcommerce.model.product.brandMetafields.BrandMetafieldWrapper;
import com.dft.bigcommerce.model.product.brandMetafields.BrandMetafieldsWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceBrandMetafield extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceBrandMetafield(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public BrandMetafieldsWrapper getAllBrandMetafield(Integer brandId) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/catalog/brands/" + brandId + "/metafields");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<BrandMetafieldsWrapper> handler = new JsonBodyHandler<>(BrandMetafieldsWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public BrandMetafieldWrapper createBrandMetafield(Integer brandId, BrandMetafieldRequest brandMetafieldRequest) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/catalog/brands/" + brandId + "/metafields");

        String jsonBody = objectMapper.writeValueAsString(brandMetafieldRequest);
        HttpRequest request = post(uriBuilder, jsonBody);
        HttpResponse.BodyHandler<BrandMetafieldWrapper> handler = new JsonBodyHandler<>(BrandMetafieldWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public BrandMetafieldWrapper getBrandMetafieldById(Integer brandId, Integer metafieldId) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "catalog/brands/" + brandId + "/metafields/" + metafieldId);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<BrandMetafieldWrapper> handler = new JsonBodyHandler<>(BrandMetafieldWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public BrandMetafieldWrapper updateBrandMetafield(Integer brandId, Integer metafieldId, BrandMetafieldRequest brandMetafieldRequest) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "catalog/brands/" + brandId + "/metafields/" + metafieldId);

        String jsonBody = objectMapper.writeValueAsString(brandMetafieldRequest);
        HttpRequest request = put(uriBuilder, jsonBody);
        HttpResponse.BodyHandler<BrandMetafieldWrapper> handler = new JsonBodyHandler<>(BrandMetafieldWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public void deleteBrandMetafield(Integer brandId, Integer metafieldId) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "catalog/brands/" + brandId + "/metafields/" + metafieldId);

        HttpRequest request = delete(uriBuilder);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }
}
