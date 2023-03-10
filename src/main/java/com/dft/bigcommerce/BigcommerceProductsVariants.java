package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.product.variant.ProductVariantRequest;
import com.dft.bigcommerce.model.product.variant.VariantRequest;
import com.dft.bigcommerce.model.product.variant.VariantWrapper;
import com.dft.bigcommerce.model.product.variantimage.VariantImageRequest;
import com.dft.bigcommerce.model.product.variantimage.VariantImageWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceProductsVariants extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceProductsVariants(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    public VariantWrapper getProductVariantById(Integer productId, Integer variantId) {
        URI uri = baseUrl("/catalog/products/" + productId + "/variants/" + variantId);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<VariantWrapper> handler = new JsonBodyHandler<>(VariantWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public VariantWrapper updateProductVariant(Integer productId, Integer variantId, ProductVariantRequest variantRequest) {
        URI uri = baseUrl("/catalog/products/" + productId + "/variants/" + variantId);

        String jsonBody = objectMapper.writeValueAsString(variantRequest);
        HttpRequest request = put(uri, jsonBody);
        HttpResponse.BodyHandler<VariantWrapper> handler = new JsonBodyHandler<>(VariantWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public VariantWrapper createProductVariant(VariantRequest productRequest, Integer id) {
        URI uri = baseUrl("catalog/products/" + id + "/variants");

        String jsonBody = objectMapper.writeValueAsString(productRequest);
        HttpRequest request = post(uri, jsonBody);
        HttpResponse.BodyHandler<VariantWrapper> handler = new JsonBodyHandler<>(VariantWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public void deleteVariant(Integer productId, Integer variantId) {
        URI uri = baseUrl("catalog/products/" + productId + "/variants/" + variantId);
        HttpRequest request = delete(uri);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    public VariantImageWrapper createVariantImage(VariantImageRequest variantImageRequest, Integer productId, Integer variantId) {
        URI uri = baseUrl("catalog/products/" + productId + "/variants/" + variantId + "/image");

        String jsonBody = objectMapper.writeValueAsString(variantImageRequest);
        HttpRequest request = post(uri, jsonBody);
        HttpResponse.BodyHandler<VariantImageWrapper> handler = new JsonBodyHandler<>(VariantImageWrapper.class);
        return getRequestWrapped(request, handler);
    }
}