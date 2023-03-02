package com.dft.bigcommerce;

import com.dft.bigcommerce.constantcodes.ConstantCode;
import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.ordersv2.orders.OrderRequest;
import com.dft.bigcommerce.model.ordersv2.orders.OrderWrapper;
import com.dft.bigcommerce.model.ordersv2.orders.OrdersWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceOrders extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String ORDER_ENDPOINT = "/orders";

    public BigcommerceOrders(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public OrderWrapper getOrderById(Integer orderId) {
        URIBuilder uriBuilder = baseUrlV2(new URIBuilder(), ORDER_ENDPOINT.concat(ConstantCode.SLASH_CHARACTER)
                .concat(orderId.toString()));

        System.out.println("uriBuilder: " + uriBuilder);
        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<OrderWrapper> handler = new JsonBodyHandler<>(OrderWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public OrderWrapper updateOrder(Integer orderId, OrderRequest orderRequest) {
        URIBuilder uriBuilder = baseUrlV2(new URIBuilder(), ORDER_ENDPOINT.concat(ConstantCode.SLASH_CHARACTER)
                .concat(orderId.toString()));

        String jsonBody = objectMapper.writeValueAsString(orderRequest);
        HttpRequest request = put(uriBuilder, jsonBody);
        HttpResponse.BodyHandler<OrderWrapper> handler = new JsonBodyHandler<>(OrderWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public void archiveAnOrder(Integer orderId) {
        URIBuilder uriBuilder = baseUrlV2(new URIBuilder(), ORDER_ENDPOINT.concat(ConstantCode.SLASH_CHARACTER)
                .concat(orderId.toString()));

        HttpRequest request = delete(uriBuilder);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    public OrdersWrapper getAllOrder() {
        URIBuilder uriBuilder = baseUrlV2(new URIBuilder(), ORDER_ENDPOINT);

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<OrdersWrapper> handler = new JsonBodyHandler<>(OrdersWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public OrderWrapper createOrder(OrderRequest orderRequest) {
        URIBuilder uriBuilder = baseUrlV2(new URIBuilder(), ORDER_ENDPOINT);

        String jsonBody = objectMapper.writeValueAsString(orderRequest);
        HttpRequest request = post(uriBuilder, jsonBody);
        HttpResponse.BodyHandler<OrderWrapper> handler = new JsonBodyHandler<>(OrderWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public void deleteAllOrders() {
        URIBuilder uriBuilder = baseUrlV2(new URIBuilder(), ORDER_ENDPOINT);

        HttpRequest request = delete(uriBuilder);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }
}
