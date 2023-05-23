package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.credentials.BigcommerceCredentials;
import com.dft.bigcommerce.model.product.productmeta.ProductMetaWrapper;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BigcommerceProductMetaField extends BigcommerceSDK {

    public BigcommerceProductMetaField(BigcommerceCredentials credentials) {
        super(credentials);
    }

    @SneakyThrows
    public ProductMetaWrapper getProductMetaFieldsByProductId(Integer productId) {
        URI uri = baseUrl("/catalog/products/" + productId + "/metafields");
        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<ProductMetaWrapper> handler = new JsonBodyHandler<>(ProductMetaWrapper.class);
        return getRequestWrapped(request, handler);
    }
}