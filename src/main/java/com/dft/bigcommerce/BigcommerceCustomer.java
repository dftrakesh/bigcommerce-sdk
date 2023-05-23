package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.credentials.BigcommerceCredentials;
import com.dft.bigcommerce.model.customer.CustomerWrapper;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class BigcommerceCustomer extends BigcommerceSDK {

    private static final String CUSTOMERS_ENDPOINT = "/customers";

    public BigcommerceCustomer(BigcommerceCredentials credentials) {
        super(credentials);
    }

    @SneakyThrows
    public CustomerWrapper getAllCustomer(HashMap<String, String> params) {

        URI uri = baseUrl(CUSTOMERS_ENDPOINT);
        uri = addParameters(uri, params);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<CustomerWrapper> handler = new JsonBodyHandler<>(CustomerWrapper.class);
        return getRequestWrapped(request, handler);
    }
}