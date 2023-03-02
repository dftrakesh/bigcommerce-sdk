package com.dft.bigcommerce;

import com.dft.bigcommerce.constantcodes.ConstantCode;
import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.product.Product;
import com.dft.bigcommerce.model.product.ProductsWrapper;
import lombok.SneakyThrows;
import org.apache.http.HttpHeaders;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BigcommerceSDK {

    private final String accessToken;
    private final String storeHash;
    private final HttpClient client;
    int MAX_ATTEMPTS = 50;
    int TIME_OUT_DURATION = 60000;


    public BigcommerceSDK(String storeHash, String accessToken) {
        this.accessToken = accessToken;
        this.storeHash = storeHash;
        client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(20))
            .build();
    }

    @SneakyThrows
    public List<Product> getPaginatedProducts(String path) {
        List<Product> productList = new ArrayList<>();
        Integer next = 1;
        Integer totalPages = 1;
        while (true) {
            URIBuilder uriBuilder = baseUrl(new URIBuilder(), path)
                .addParameter("limit", "250")
                .addParameter("page", next.toString());

            HttpRequest request = get(uriBuilder);
            HttpResponse.BodyHandler<ProductsWrapper> handler = new JsonBodyHandler<>(ProductsWrapper.class);
            ProductsWrapper productsWrapper = getRequestWrapped(request, handler);
            totalPages = productsWrapper.getMeta().getPagination().getTotalPages();
            productList.addAll(productsWrapper.getData());
            next++;
            if (next == totalPages + 1) {
                break;
            }
        }
        return productList;
    }

    protected URIBuilder baseUrl(URIBuilder uriBuilder, String path) {
        return uriBuilder
            .setScheme("https")
            .setHost(ConstantCode.BASE_ENDPOINT + this.storeHash + ConstantCode.VERSION_3)
            .setPath(path);
    }

    protected URIBuilder baseUrlV2(URIBuilder uriBuilder, String path) {
        return uriBuilder
                .setScheme("https")
                .setHost(ConstantCode.BASE_ENDPOINT + this.storeHash + ConstantCode.VERSION_2)
                .setPath(path);
    }

    @SneakyThrows
    protected HttpRequest get(URIBuilder uriBuilder) {
        return HttpRequest.newBuilder(uriBuilder.build())
            .header(ConstantCode.AUTH_TOKEN, this.accessToken)
            .header(HttpHeaders.ACCEPT, "application/json")
            .GET()
            .build();
    }

    @SneakyThrows
    protected HttpRequest post(URIBuilder uriBuilder, final String jsonBody) {

        return HttpRequest.newBuilder(uriBuilder.build())
            .header(ConstantCode.AUTH_TOKEN, this.accessToken)
            .header(HttpHeaders.CONTENT_TYPE, "application/json")
            .header(HttpHeaders.ACCEPT, "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();
    }

    @SneakyThrows
    protected HttpRequest put(URIBuilder uriBuilder, final String jsonBody) {

        return HttpRequest.newBuilder(uriBuilder.build())
            .header(ConstantCode.AUTH_TOKEN, this.accessToken)
            .header(HttpHeaders.CONTENT_TYPE, "application/json")
            .header(HttpHeaders.ACCEPT, "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();
    }

    @SneakyThrows
    protected HttpRequest delete(URIBuilder uriBuilder) {

        return HttpRequest.newBuilder(uriBuilder.build())
            .header(ConstantCode.AUTH_TOKEN, this.accessToken)
            .header(HttpHeaders.CONTENT_TYPE, "application/json")
            .header(HttpHeaders.ACCEPT, "application/json")
            .DELETE()
            .build();
    }

    @SneakyThrows
    public <T> T getRequestWrapped(HttpRequest request, HttpResponse.BodyHandler<T> handler) {

        return client
            .sendAsync(request, handler)
            .thenComposeAsync(response -> tryResend(client, request, handler, response, 1))
            .get()
            .body();
    }

    @SneakyThrows
    public <T> CompletableFuture<HttpResponse<T>> tryResend(HttpClient client,
                                                            HttpRequest request,
                                                            HttpResponse.BodyHandler<T> handler,
                                                            HttpResponse<T> resp, int count) {
        if (resp.statusCode() == 429 && count < MAX_ATTEMPTS) {
            Thread.sleep(TIME_OUT_DURATION);
            return client.sendAsync(request, handler)
                .thenComposeAsync(response -> tryResend(client, request, handler, response, count + 1));
        }
        return CompletableFuture.completedFuture(resp);
    }
}