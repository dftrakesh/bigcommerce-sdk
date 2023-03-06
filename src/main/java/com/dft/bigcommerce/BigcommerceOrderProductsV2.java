package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.ordersv2.orderproducts.OrderProductWrapper;
import com.dft.bigcommerce.model.ordersv2.orderproducts.OrderProductsWrapper;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.dft.bigcommerce.constantcodes.ConstantCode.*;

public class BigcommerceOrderProductsV2 extends BigcommerceSDK {

    public BigcommerceOrderProductsV2(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public OrderProductsWrapper getAllOrderProducts(Integer orderId) {
        URIBuilder uriBuilder = baseUrlV2(new URIBuilder(), ORDERS_ENDPOINT.concat(SLASH_CHARACTER)
                .concat(orderId.toString())
                .concat(PRODUCTS_ENDPOINT));

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<OrderProductsWrapper> handler = new JsonBodyHandler<>(OrderProductsWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public OrderProductWrapper getOrderProductById(Integer orderId, Integer productId) {
        URIBuilder uriBuilder = baseUrlV2(new URIBuilder(), ORDERS_ENDPOINT.concat(SLASH_CHARACTER)
                .concat(orderId.toString())
                .concat(PRODUCTS_ENDPOINT)
                .concat(SLASH_CHARACTER)
                .concat(productId.toString()));

        HttpRequest request = get(uriBuilder);
        HttpResponse.BodyHandler<OrderProductWrapper> handler = new JsonBodyHandler<>(OrderProductWrapper.class);
        return getRequestWrapped(request, handler);
    }
}
