package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.ordersv2.ordershippingaddresses.OrderShippingAddressWrapper;
import com.dft.bigcommerce.model.ordersv2.ordershippingaddresses.OrderShippingAddressesWrapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceOrderShippingAddressesV2 extends BigcommerceSDK {

    private static final String FORWARD_SLASH_CHARACTER = "/";
    private static final String ORDERS_ENDPOINT = "/orders";
    private static final String SHIPPING_ADDRESSES_ENDPOINT = "/shipping_addresses";

    public BigcommerceOrderShippingAddressesV2(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public OrderShippingAddressesWrapper getOrderShippingAddressesById(Integer orderId) {
        URI uri = baseUrlV2((ORDERS_ENDPOINT).concat(FORWARD_SLASH_CHARACTER)
                .concat(orderId.toString())
                .concat(SHIPPING_ADDRESSES_ENDPOINT));

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<OrderShippingAddressesWrapper> handler = new JsonBodyHandler<>(OrderShippingAddressesWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public OrderShippingAddressWrapper getOrderShippingAddressesByShippingAddressId(Integer orderId, Integer shippingAddressId) {
        URI uri = baseUrlV2((ORDERS_ENDPOINT).concat(FORWARD_SLASH_CHARACTER)
                .concat(orderId.toString())
                .concat(SHIPPING_ADDRESSES_ENDPOINT).concat(FORWARD_SLASH_CHARACTER)
                .concat(shippingAddressId.toString()));

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<OrderShippingAddressWrapper> handler = new JsonBodyHandler<>(OrderShippingAddressWrapper.class);
        return getRequestWrapped(request, handler);
    }
}
