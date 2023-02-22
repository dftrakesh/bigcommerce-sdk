package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.product.variant.ProductVariantRequest;
import com.dft.bigcommerce.model.product.variant.VariantWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceProductsVariants extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceProductsVariants(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    public VariantWrapper getProductVariantById(Integer productId, Integer variantId) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/catalog/products/" + productId + "/variants/" + variantId);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<VariantWrapper> handler = new JsonBodyHandler<>(VariantWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public VariantWrapper updateProductVariant(Integer productId, Integer variantId, ProductVariantRequest variantRequest) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "/catalog/products/" + productId + "/variants/" + variantId);

        String jsonBody = objectMapper.writeValueAsString(variantRequest);
        HttpRequest request = put(uriBuilder, jsonBody);
        HttpResponse.BodyHandler<VariantWrapper> handler = new JsonBodyHandler<>(VariantWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public void deleteVariant(Integer productId, Integer variantId) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "catalog/products/" + productId + "/variants/" + variantId);
        HttpRequest request = delete(uriBuilder);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }
}