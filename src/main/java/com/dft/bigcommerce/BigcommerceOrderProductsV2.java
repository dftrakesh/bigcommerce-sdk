package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.ordersv2.orderproducts.OrderProductWrapper;
import com.dft.bigcommerce.model.ordersv2.orderproducts.OrderProductsWrapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.dft.bigcommerce.constantcodes.ConstantCode.*;

public class BigcommerceOrderProductsV2 extends BigcommerceSDK {

    public BigcommerceOrderProductsV2(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public OrderProductsWrapper getAllOrderProducts(Integer orderId) {
        URI uri = baseUrlV2(ORDERS_ENDPOINT.concat(SLASH_CHARACTER)
                .concat(orderId.toString())
                .concat(PRODUCTS_ENDPOINT));

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<OrderProductsWrapper> handler = new JsonBodyHandler<>(OrderProductsWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public OrderProductWrapper getOrderProductById(Integer orderId, Integer productId) {
        URI uri = baseUrlV2(ORDERS_ENDPOINT.concat(SLASH_CHARACTER)
                .concat(orderId.toString())
                .concat(PRODUCTS_ENDPOINT)
                .concat(SLASH_CHARACTER)
                .concat(productId.toString()));

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<OrderProductWrapper> handler = new JsonBodyHandler<>(OrderProductWrapper.class);
        return getRequestWrapped(request, handler);
    }
}
