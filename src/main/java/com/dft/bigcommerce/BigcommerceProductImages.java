package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.credentials.BigcommerceCredentials;
import com.dft.bigcommerce.model.product.productImages.ProductImageRequest;
import com.dft.bigcommerce.model.product.productImages.ProductImageWrapper;
import com.dft.bigcommerce.model.product.productImages.ProductImagesWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import java.io.File;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BigcommerceProductImages extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceProductImages(BigcommerceCredentials credentials) {
        super(credentials);
    }

    @SneakyThrows
    public ProductImageWrapper createProductImage(Integer productId, ProductImageRequest productImageRequest) {
        URI uri = baseUrl("/catalog/products/" + productId + "/images");

        String jsonBody = objectMapper.writeValueAsString(productImageRequest);
        HttpRequest request = post(uri, jsonBody);

        HttpResponse.BodyHandler<ProductImageWrapper> handler = new JsonBodyHandler<>(ProductImageWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public ProductImageWrapper createProductImage(Integer productId, File imageFile) {
        URI uri = baseUrl("/catalog/products/" + productId + "/images");

        byte[] imageData = Files.readAllBytes(Paths.get(imageFile.toURI()));
        HttpRequest request = postMultipart(uri, imageData, imageFile);

        HttpResponse.BodyHandler<ProductImageWrapper> handler = new JsonBodyHandler<>(ProductImageWrapper.class);
        return getRequestWrapped(request, handler);
    }


    @SneakyThrows
    public ProductImagesWrapper getProductImageByProductId(Integer productId) {
        URI uri = baseUrl("/catalog/products/" + productId + "/images");
        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<ProductImagesWrapper> handler = new JsonBodyHandler<>(ProductImagesWrapper.class);
        return getRequestWrapped(request, handler);
    }
}