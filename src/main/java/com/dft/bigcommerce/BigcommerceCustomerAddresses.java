package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.customer.customerAddresses.CustomerAddressesWrapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceCustomerAddresses extends BigcommerceSDK {

    private static final String FORWARD_SLASH_CHARACTER = "/";
    private static final String CUSTOMERS_ENDPOINT = "/customers";
    private static final String CUSTOMER_ADDRESSES_ENDPOINT = "/addresses";

    public BigcommerceCustomerAddresses(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public CustomerAddressesWrapper getAllCustomerAddress(Integer customerId) {
        URI uri = baseUrlV2(CUSTOMERS_ENDPOINT + FORWARD_SLASH_CHARACTER
                + customerId.toString()
                + CUSTOMER_ADDRESSES_ENDPOINT);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<CustomerAddressesWrapper> handler = new JsonBodyHandler<>(CustomerAddressesWrapper.class);
        return getRequestWrapped(request, handler);
    }
}
