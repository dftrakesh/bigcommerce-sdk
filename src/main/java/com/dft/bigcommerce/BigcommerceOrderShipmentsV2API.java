package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.credentials.BigcommerceCredentials;
import com.dft.bigcommerce.model.ordersv2.ordershipments.OrderShipmentRequest;
import com.dft.bigcommerce.model.ordersv2.ordershipments.OrderShipmentWrapper;
import com.dft.bigcommerce.model.ordersv2.ordershipments.OrderShipmentsWrapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceOrderShipmentsV2API extends BigcommerceSDK {

    private static final String ORDERS_ENDPOINT = "/orders";
    private static final String FORWARD_SLASH_CHARACTER = "/";
    private static final String SHIPMENTS_ENDPOINT = "/shipments";
    
    public BigcommerceOrderShipmentsV2API(BigcommerceCredentials credentials) {
        super(credentials);
    }
    
    public OrderShipmentsWrapper createOrderShipment(Integer orderId, OrderShipmentRequest orderShipmentRequest) {
        URI uri = baseUrlV2(ORDERS_ENDPOINT + FORWARD_SLASH_CHARACTER + orderId + SHIPMENTS_ENDPOINT);
        System.out.println("uri = " + uri);

        HttpRequest request = postWithObject(uri, orderShipmentRequest);
        HttpResponse.BodyHandler<OrderShipmentsWrapper> handler = new JsonBodyHandler<>(OrderShipmentsWrapper.class);
        return getRequestWrapped(request, handler);
    }

    public OrderShipmentsWrapper getOrderShipment(Integer orderId) {
        URI uri = baseUrlV2(ORDERS_ENDPOINT + FORWARD_SLASH_CHARACTER + orderId + SHIPMENTS_ENDPOINT);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<OrderShipmentsWrapper> handler = new JsonBodyHandler<>(OrderShipmentsWrapper.class);
        return getRequestWrapped(request, handler);
    }
}