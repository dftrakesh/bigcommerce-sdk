package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.ordersv2.orders.OrderRequest;
import com.dft.bigcommerce.model.ordersv2.orders.OrderWrapper;
import com.dft.bigcommerce.model.ordersv2.orders.OrdersWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class BigcommerceOrders extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String ORDERS_ENDPOINT = "/orders";
    private static final String FORWARD_SLASH_CHARACTER = "/";

    public BigcommerceOrders(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public OrderWrapper getOrderById(Integer orderId) {
        URI uri = baseUrlV2(ORDERS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(orderId.toString()));

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<OrderWrapper> handler = new JsonBodyHandler<>(OrderWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public OrderWrapper updateOrder(Integer orderId, OrderRequest orderRequest) {
        URI uri = baseUrlV2(ORDERS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(orderId.toString()));

        String jsonBody = objectMapper.writeValueAsString(orderRequest);
        HttpRequest request = put(uri, jsonBody);
        HttpResponse.BodyHandler<OrderWrapper> handler = new JsonBodyHandler<>(OrderWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public void archiveAnOrder(Integer orderId) {
        URI uri = baseUrlV2(ORDERS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(orderId.toString()));

        HttpRequest request = delete(uri);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    public OrdersWrapper getAllOrder(HashMap<String, String> params) {
        URI uri = baseUrlV2(ORDERS_ENDPOINT);
        uri = addParameters(uri, params);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<OrdersWrapper> handler = new JsonBodyHandler<>(OrdersWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public OrderWrapper createOrder(OrderRequest orderRequest) {
        URI uri = baseUrlV2(ORDERS_ENDPOINT);

        String jsonBody = objectMapper.writeValueAsString(orderRequest);
        HttpRequest request = post(uri, jsonBody);
        HttpResponse.BodyHandler<OrderWrapper> handler = new JsonBodyHandler<>(OrderWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public void deleteAllOrders() {
        URI uri = baseUrlV2(ORDERS_ENDPOINT);

        HttpRequest request = delete(uri);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }
}
