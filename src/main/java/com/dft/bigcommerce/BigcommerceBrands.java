package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.product.brand.Brand;
import com.dft.bigcommerce.model.product.brand.BrandRequest;
import com.dft.bigcommerce.model.product.brand.BrandWrapper;
import com.dft.bigcommerce.model.product.brand.BrandsWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class BigcommerceBrands extends BigcommerceSDK {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String FORWARD_SLASH_CHARACTER = "/";
    private static final String CATALOG_BRANDS_ENDPOINT = "/catalog/brands";

    public BigcommerceBrands(String storeHash, String accessToken) {
        super(storeHash, accessToken);
    }

    @SneakyThrows
    public BrandsWrapper getAllBrand(HashMap<String, String> params) {
        URI uri = baseUrl(CATALOG_BRANDS_ENDPOINT);
        uri = addParameters(uri, params);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<BrandsWrapper> handler = new JsonBodyHandler<>(BrandsWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public Brand createBrand(BrandRequest brandRequest) {
        URI uri = baseUrl("/catalog/brands");

        String jsonBody = objectMapper.writeValueAsString(brandRequest);
        HttpRequest request = post(uri, jsonBody);
        HttpResponse.BodyHandler<BrandWrapper> handler = new JsonBodyHandler<>(BrandWrapper.class);
        BrandWrapper brandWrapper = getRequestWrapped(request, handler);
        return brandWrapper.getData();
    }

    @SneakyThrows
    public Brand getBrandById(Integer brandId, HashMap<String, String> params) {
        URI uri = baseUrl(CATALOG_BRANDS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(brandId.toString()));
        uri = addParameters(uri, params);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<BrandWrapper> handler = new JsonBodyHandler<>(BrandWrapper.class);
        BrandWrapper brandWrapper = getRequestWrapped(request, handler);
        return brandWrapper.getData();
    }

    @SneakyThrows
    public Brand updateBrand(Integer brandId, BrandRequest brandRequest) {
        URI uri = baseUrl("/catalog/brands/" + brandId);

        String jsonBody = objectMapper.writeValueAsString(brandRequest);
        HttpRequest request = put(uri, jsonBody);
        HttpResponse.BodyHandler<BrandWrapper> handler = new JsonBodyHandler<>(BrandWrapper.class);
        BrandWrapper brandWrapper = getRequestWrapped(request, handler);
        return brandWrapper.getData();
    }

    @SneakyThrows
    public void deleteBrandById(Integer brandId) {
        URI uri = baseUrl(CATALOG_BRANDS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(brandId.toString()));

        HttpRequest request = delete(uri);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    public void deleteAllBrand() {
        URI uri = baseUrl(CATALOG_BRANDS_ENDPOINT);

        HttpRequest request = delete(uri);
        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }
}
