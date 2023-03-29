package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.product.variant.ProductVariantRequest;
import com.dft.bigcommerce.model.product.variant.VariantRequest;
import com.dft.bigcommerce.model.product.variant.VariantWrapper;
import com.dft.bigcommerce.model.product.variant.VariantsWrapper;
import com.dft.bigcommerce.model.product.variantimage.VariantImageRequest;
import com.dft.bigcommerce.model.product.variantimage.VariantImageWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class BigcommerceProductsVariants extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String FORWARD_SLASH_CHARACTER = "/";
    private static final String VARIANTS_ENDPOINT = "/variants";
    private static final String CATALOG_PRODUCTS_ENDPOINT = "/catalog/products";

    public BigcommerceProductsVariants(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    public VariantsWrapper getAllProductVariant(Integer productId, HashMap<String, String> params) {
        URI uri = baseUrl(CATALOG_PRODUCTS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(productId.toString())
                .concat(VARIANTS_ENDPOINT));
        uri = addParameters(uri, params);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<VariantsWrapper> handler = new JsonBodyHandler<>(VariantsWrapper.class);
        return getRequestWrapped(request, handler);
    }

    public VariantWrapper getProductVariantById(Integer productId, Integer variantId, HashMap<String, String> params) {
        URI uri = baseUrl(CATALOG_PRODUCTS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(productId.toString())
                .concat(VARIANTS_ENDPOINT)
                .concat(FORWARD_SLASH_CHARACTER)
                .concat(variantId.toString()));
        uri = addParameters(uri, params);

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