package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.ordersv2.orderProducts.OrderProductWrapper;
import com.dft.bigcommerce.model.ordersv2.orderProducts.OrderProductsWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceOrderV2Products extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceOrderV2Products(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public OrderProductsWrapper getAllOrderProducts(Integer orderId) {
        URIBuilder uriBuilder = baseUrlV2(new URIBuilder(), "/orders/" + orderId + "/products");

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<OrderProductsWrapper> handler = new JsonBodyHandler<>(OrderProductsWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public OrderProductWrapper getOrderProductById(Integer orderId, Integer productId) {
        URIBuilder uriBuilder = baseUrlV2(new URIBuilder(), "/orders/" + orderId + "/products/" + productId);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<OrderProductWrapper> handler = new JsonBodyHandler<>(OrderProductWrapper.class);
        return getRequestWrapped(request, handler);
    }
}
