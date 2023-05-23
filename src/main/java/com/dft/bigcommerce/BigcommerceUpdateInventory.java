package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.credentials.BigcommerceCredentials;
import com.dft.bigcommerce.model.product.ProductRequest;
import com.dft.bigcommerce.model.product.ProductWrapper;
import com.dft.bigcommerce.model.product.variant.ProductVariantRequest;
import com.dft.bigcommerce.model.product.variant.VariantWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceUpdateInventory extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String CATALOG_ENDPOINT = "/catalog";
    private static final String PRODUCTS_ENDPOINT = "/products";
    private static final String VARIANTS_ENDPOINT = "/variants";
    private static final String FORWARD_SLASH_CHARACTER = "/";

    public BigcommerceUpdateInventory(BigcommerceCredentials credentials) {
        super(credentials);
    }

    @SneakyThrows
    public ProductWrapper updateProductInventory(ProductRequest productRequest, Integer productId) {
        URI uri = baseUrl(CATALOG_ENDPOINT
            .concat(PRODUCTS_ENDPOINT)
            .concat(FORWARD_SLASH_CHARACTER)
            .concat(productId.toString()));

        String jsonBody = objectMapper.writeValueAsString(productRequest);
        HttpRequest request = put(uri, jsonBody);
        HttpResponse.BodyHandler<ProductWrapper> handler = new JsonBodyHandler<>(ProductWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public VariantWrapper updateProductVariantInventory(ProductVariantRequest productVariantRequest, Integer productId, Integer variantId) {
        URI uri = baseUrl(CATALOG_ENDPOINT.concat(PRODUCTS_ENDPOINT)
                .concat(FORWARD_SLASH_CHARACTER)
                .concat(productId.toString())
                .concat(VARIANTS_ENDPOINT)
                .concat(FORWARD_SLASH_CHARACTER)
                .concat(variantId.toString()));

        String jsonBody = objectMapper.writeValueAsString(productVariantRequest);
        HttpRequest request = put(uri, jsonBody);
        HttpResponse.BodyHandler<VariantWrapper> handler = new JsonBodyHandler<>(VariantWrapper.class);
        return getRequestWrapped(request, handler);
    }
}