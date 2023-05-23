package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.credentials.BigcommerceCredentials;
import com.dft.bigcommerce.model.storeinformationv2.StoreInformationWrapper;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.http.HttpRequest;

public class BigcommerceStoreInformationV2 extends BigcommerceSDK {

    private static final String STORE_ENDPOINT = "/store";

    public BigcommerceStoreInformationV2(BigcommerceCredentials credentials) {
        super(credentials);
    }

    @SneakyThrows
    public StoreInformationWrapper getStoreInformation() {
        URI uri = baseUrlV2(STORE_ENDPOINT);

        HttpRequest request = get(uri);
        JsonBodyHandler<StoreInformationWrapper> handler = new JsonBodyHandler<>(StoreInformationWrapper.class);
        return getRequestWrapped(request, handler);
    }
}