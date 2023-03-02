package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.product.Product;
import com.dft.bigcommerce.model.product.ProductRequest;
import com.dft.bigcommerce.model.product.ProductWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class BigcommerceProducts extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceProducts(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    public List<Product> getProducts() {
        return getPaginatedProducts("catalog/products");
    }

    public Product getProductById(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "catalog/products/" + id);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<ProductWrapper> handler = new JsonBodyHandler<>(ProductWrapper.class);
        ProductWrapper productWrapper = getRequestWrapped(request, handler);
        return productWrapper.getData();
    }

    @SneakyThrows
    public ProductWrapper createProduct(ProductRequest productRequest) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "catalog/products");

        String jsonBody = objectMapper.writeValueAsString(productRequest);
        HttpRequest request = post(uriBuilder, jsonBody);
        HttpResponse.BodyHandler<ProductWrapper> handler = new JsonBodyHandler<>(ProductWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public ProductWrapper updateProduct(ProductRequest productRequest, Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "catalog/products/" + id);

        String jsonBody = objectMapper.writeValueAsString(productRequest);
        HttpRequest request = put(uriBuilder, jsonBody);
        HttpResponse.BodyHandler<ProductWrapper> handler = new JsonBodyHandler<>(ProductWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public void deleteProduct(Integer id) {
        URIBuilder uriBuilder = baseUrl(new URIBuilder(), "catalog/products/" + id);
        HttpRequest request = delete(uriBuilder);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }
}