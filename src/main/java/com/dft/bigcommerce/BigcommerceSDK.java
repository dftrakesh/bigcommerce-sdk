package com.dft.bigcommerce;

import com.dft.bigcommerce.exception.NotFoundException;
import com.dft.bigcommerce.exception.UnProcessableEntityException;
import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.product.Product;
import com.dft.bigcommerce.model.product.ProductsWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
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
    private final ObjectMapper objectMapper = new ObjectMapper();
    int MAX_ATTEMPTS = 50;
    int TIME_OUT_DURATION = 60000;
    private static final String MEDIA_TYPE = "application/json;charset=UTF-8";


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
            .setHost("api.bigcommerce.com/stores/" + this.storeHash + "/v3")
            .setPath(path);
    }

    @SneakyThrows
    protected HttpRequest get(URIBuilder uriBuilder) {
        return HttpRequest.newBuilder(uriBuilder.build())
            .header("X-Auth-Token", this.accessToken)
            .GET()
            .build();
    }

    @SneakyThrows
    protected HttpRequest post(URIBuilder uriBuilder, final String jsonBody) {

        return HttpRequest.newBuilder(uriBuilder.build())
            .header("X-Auth-Token", this.accessToken)
            .header(HttpHeaders.CONTENT_TYPE, "application/json")
            .header(HttpHeaders.ACCEPT, "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();
    }

    @SneakyThrows
    protected HttpRequest put(URIBuilder uriBuilder, final String jsonBody) {

        return HttpRequest.newBuilder(uriBuilder.build())
            .header("X-Auth-Token", this.accessToken)
            .header(HttpHeaders.CONTENT_TYPE, "application/json")
            .header(HttpHeaders.ACCEPT, "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();
    }

    @SneakyThrows
    protected HttpRequest delete(URIBuilder uriBuilder) {

        return HttpRequest.newBuilder(uriBuilder.build())
            .header("X-Auth-Token", this.accessToken)
            .header(HttpHeaders.CONTENT_TYPE, "application/json")
            .header(HttpHeaders.ACCEPT, "application/json")
            .DELETE()
            .build();
    }

    @SneakyThrows
    public <T> T getRequestWrapped(HttpRequest request, HttpResponse.BodyHandler<T> handler) {

        return client
            .sendAsync(request, handler)
            .thenComposeAsync(response -> handleUnknownError(client, request, handler, response))
            .get();
    }

    @SneakyThrows
    public <T> CompletableFuture<T> handleUnknownError(HttpClient client,
                                                       HttpRequest request,
                                                       HttpResponse.BodyHandler<T> handler,
                                                       HttpResponse<T> resp) {
        if (resp.statusCode() == HttpStatus.SC_OK) {
            return CompletableFuture.completedFuture(resp.body());
        }
        if (resp.statusCode() == HttpStatus.SC_NO_CONTENT) {
            return CompletableFuture.completedFuture(resp.body());
        }
        if (resp.statusCode() == HttpStatus.SC_NOT_FOUND) {
            throw new NotFoundException(objectMapper.writeValueAsString(resp.body()));
        }
        if (resp.statusCode() == HttpStatus.SC_UNPROCESSABLE_ENTITY) {
            throw new UnProcessableEntityException(objectMapper.writeValueAsString(resp.body()));
        }
        if (resp.statusCode() == 429) {
            return CompletableFuture.completedFuture(tryResend(client, request, handler, resp, 1).get().body());
        }

        throw new Exception();
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
