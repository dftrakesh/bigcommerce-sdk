package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.product.Product;
import com.dft.bigcommerce.model.product.ProductRequest;
import com.dft.bigcommerce.model.product.ProductWrapper;
import com.dft.bigcommerce.model.product.bulkpricingrule.ProductBulkPricingRulesWrapper;
import com.dft.bigcommerce.model.product.productImages.ProductImagesWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceProducts extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceProducts(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    public Product getProductById(Integer id) {
        URI uri = baseUrl("/catalog/products/" + id);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<ProductWrapper> handler = new JsonBodyHandler<>(ProductWrapper.class);
        ProductWrapper productWrapper = getRequestWrapped(request, handler);
        return productWrapper.getData();
    }

    @SneakyThrows
    public ProductWrapper createProduct(ProductRequest productRequest) {
        URI uri = baseUrl("/catalog/products");

        String jsonBody = objectMapper.writeValueAsString(productRequest);
        HttpRequest request = post(uri, jsonBody);
        HttpResponse.BodyHandler<ProductWrapper> handler = new JsonBodyHandler<>(ProductWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public ProductWrapper updateProduct(ProductRequest productRequest, Integer id) {
        URI uri = baseUrl("/catalog/products/" + id);

        String jsonBody = objectMapper.writeValueAsString(productRequest);
        HttpRequest request = put(uri, jsonBody);
        HttpResponse.BodyHandler<ProductWrapper> handler = new JsonBodyHandler<>(ProductWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public void deleteProduct(Integer id) {
        URI uri = baseUrl("/catalog/products/" + id);
        HttpRequest request = delete(uri);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    public ProductBulkPricingRulesWrapper getBulkPricingRulesByProductId(Integer productId) {
        URI uri = baseUrl("/catalog/products/" + productId + "/bulk-pricing-rules");
        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<ProductBulkPricingRulesWrapper> handler = new JsonBodyHandler<>(ProductBulkPricingRulesWrapper.class);
        return getRequestWrapped(request, handler);
    }
}