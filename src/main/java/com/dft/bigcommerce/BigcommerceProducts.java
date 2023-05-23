package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.credentials.BigcommerceCredentials;
import com.dft.bigcommerce.model.product.Product;
import com.dft.bigcommerce.model.product.ProductRequest;
import com.dft.bigcommerce.model.product.ProductWrapper;
import com.dft.bigcommerce.model.product.ProductsWrapper;
import com.dft.bigcommerce.model.product.bulkpricingrule.ProductBulkPricingRulesWrapper;
import com.dft.bigcommerce.model.product.customfields.ProductCustomFieldWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class BigcommerceProducts extends BigcommerceSDK {

    private static final String FORWARD_SLASH_CHARACTER = "/";
    private static final String CATALOG_PRODUCTS_ENDPOINT = "/catalog/products";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceProducts(BigcommerceCredentials credentials) {
        super(credentials);
    }

    public Product getProductById(Integer productId, HashMap<String, String> params) {
        URI uri = baseUrl(CATALOG_PRODUCTS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
            .concat(productId.toString()));
        uri = addParameters(uri, params);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<ProductWrapper> handler = new JsonBodyHandler<>(ProductWrapper.class);
        ProductWrapper productWrapper = getRequestWrapped(request, handler);
        return productWrapper.getData();
    }

    @SneakyThrows
    public ProductWrapper createProduct(ProductRequest productRequest) {
        URI uri = baseUrl(CATALOG_PRODUCTS_ENDPOINT);

        String jsonBody = objectMapper.writeValueAsString(productRequest);
        HttpRequest request = post(uri, jsonBody);
        HttpResponse.BodyHandler<ProductWrapper> handler = new JsonBodyHandler<>(ProductWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public ProductWrapper updateProduct(ProductRequest productRequest, Integer productId) {
        URI uri = baseUrl(CATALOG_PRODUCTS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(productId.toString()));

        String jsonBody = objectMapper.writeValueAsString(productRequest);
        HttpRequest request = put(uri, jsonBody);
        HttpResponse.BodyHandler<ProductWrapper> handler = new JsonBodyHandler<>(ProductWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public void deleteProduct(Integer productId) {
        URI uri = baseUrl(CATALOG_PRODUCTS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(productId.toString()));

        HttpRequest request = delete(uri);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    public ProductsWrapper getAllProduct(HashMap<String, String> params) {
        URI uri = baseUrl(CATALOG_PRODUCTS_ENDPOINT);
        uri = addParameters(uri, params);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<ProductsWrapper> handler = new JsonBodyHandler<>(ProductsWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public ProductBulkPricingRulesWrapper getBulkPricingRulesByProductId(Integer productId) {
        URI uri = baseUrl("/catalog/products/" + productId + "/bulk-pricing-rules");
        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<ProductBulkPricingRulesWrapper> handler = new JsonBodyHandler<>(ProductBulkPricingRulesWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public ProductCustomFieldWrapper getCustomFields(Integer productId) {
        URI uri = baseUrl("/catalog/products/" + productId + "/custom-fields");
        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<ProductCustomFieldWrapper> handler = new JsonBodyHandler<>(ProductCustomFieldWrapper.class);
        return getRequestWrapped(request, handler);
    }
}