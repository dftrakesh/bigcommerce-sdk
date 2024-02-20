package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.credentials.BigcommerceCredentials;
import com.dft.bigcommerce.model.ordersv2.transaction.OrderTransactionsWrapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceOrderTransaction extends BigcommerceSDK {
    private static final String FORWARD_SLASH_CHARACTER = "/";
    private static final String ORDERS_ENDPOINT = "/orders";
    private static final String TRANSACTION_ENDPOINT = "/transactions";

    public BigcommerceOrderTransaction(BigcommerceCredentials credentials) {
        super(credentials);
    }

    @SneakyThrows
    public OrderTransactionsWrapper getOrderTransaction(Integer orderId) {
        URI uri = baseUrl((ORDERS_ENDPOINT).concat(FORWARD_SLASH_CHARACTER)
                .concat(orderId.toString())
                .concat(TRANSACTION_ENDPOINT));

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<OrderTransactionsWrapper> handler = new JsonBodyHandler<>(OrderTransactionsWrapper.class);
        return getRequestWrapped(request, handler);
    }
}
