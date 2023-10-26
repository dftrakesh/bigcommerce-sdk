package com.dft.bigcommerce;

import com.dft.bigcommerce.model.credentials.BigcommerceCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.SneakyThrows;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public class BigcommerceSDK {

    int MAX_ATTEMPTS = 50;
    int TIME_OUT_DURATION = 60000;
    private final HttpClient client;
    private BigcommerceCredentials credentials;
    private static final String FORWARD_SLASH_CHARACTER = "/";
    private static final String AUTH_TOKEN = "X-Auth-Token";
    private static final String ACCEPT = "Accept";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String BASE_ENDPOINT = "api.bigcommerce.com/stores/";
    private static final String VERSION_3 = "/v3";
    private static final String VERSION_2 = "/v2";
    private static final String HTTPS = "https://";

    protected final ObjectMapper objectMapper = new ObjectMapper();

    public BigcommerceSDK() {
        client = HttpClient.newBuilder()
                           .followRedirects(HttpClient.Redirect.ALWAYS)
                           .version(HttpClient.Version.HTTP_1_1)
                           .connectTimeout(Duration.ofSeconds(20))
                           .build();
    }

    public BigcommerceSDK(BigcommerceCredentials credentials) {
        this.credentials = credentials;
        client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(20))
            .build();
    }

    @SneakyThrows
    private static byte[] buildMultipartData(String boundary, String crlf, byte[] imageData, File file) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(("--" + boundary + crlf).getBytes());
        byteArrayOutputStream.write(("Content-Disposition: form-data; name=\"image_file\"; filename=\"" + file.getName() + "\"" + crlf).getBytes());
        byteArrayOutputStream.write(("Content-Type: image/jpeg" + crlf + crlf).getBytes());
        byteArrayOutputStream.write(imageData);
        byteArrayOutputStream.write((crlf + "--" + boundary + crlf).getBytes());
        byteArrayOutputStream.write(("Content-Disposition: form-data; name=\"data\"" + crlf + crlf).getBytes());
        byteArrayOutputStream.write((crlf + "--" + boundary + "--" + crlf).getBytes());
        return byteArrayOutputStream.toByteArray();
    }

    @SneakyThrows
    protected HttpRequest postMultipart(URI uri, final byte[] imageData, final File file) {
        final String boundary = "---" + UUID.randomUUID();
        final String crlf = "\r\n";
        final HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofByteArray(buildMultipartData(boundary, crlf, imageData, file));
        return HttpRequest.newBuilder(uri)
            .header(AUTH_TOKEN, this.credentials.getAccessToken())
            .header(CONTENT_TYPE, "multipart/form-data; boundary=" + boundary)
            .header(ACCEPT, "application/json")
            .POST(body)
            .build();
    }

    @SneakyThrows
    protected URI baseUrl(String path) {
        return new URI(new StringBuilder().append(HTTPS)
            .append(BASE_ENDPOINT)
            .append(this.credentials.getStoreHash())
            .append(VERSION_3)
            .append(path)
            .toString());
    }

    @SneakyThrows
    protected URI baseUrlV2(String path) {
        return new URI(new StringBuilder().append(HTTPS)
            .append(BASE_ENDPOINT)
            .append(this.credentials.getStoreHash())
                .append(VERSION_2)
                .append(path)
                .toString());
    }

    @SneakyThrows
    protected URI addParameters(URI uri, HashMap<String, String> params) {

        String query = uri.getQuery();
        StringBuilder builder = new StringBuilder();
        if (query != null)
            builder.append(query);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String keyValueParam = entry.getKey() + "=" + entry.getValue();
            if (!builder.toString().isEmpty())
                builder.append("&");
            builder.append(keyValueParam);
        }
        return new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), builder.toString(), uri.getFragment());
    }

    protected HttpRequest get(URI uri) {
        return HttpRequest.newBuilder(uri)
                          .header(AUTH_TOKEN, this.credentials.getAccessToken())
                          .header(ACCEPT, "application/json")
                          .GET()
                          .build();
    }

    protected HttpRequest post(URI uri, final String jsonBody) {
        return HttpRequest.newBuilder(uri)
                          .header(AUTH_TOKEN, this.credentials.getAccessToken())
                          .header(CONTENT_TYPE, "application/json")
                          .header(ACCEPT, "application/json")
                          .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                          .build();
    }

    protected HttpRequest postWithOutAccessToken(URI uri, final String jsonBody) {
        return HttpRequest.newBuilder(uri)
                          .header(CONTENT_TYPE, "application/json")
                          .header(ACCEPT, "application/json")
                          .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                          .build();
    }

    protected HttpRequest put(URI uri, final String jsonBody) {
        return HttpRequest.newBuilder(uri)
                          .header(AUTH_TOKEN, this.credentials.getAccessToken())
                          .header(CONTENT_TYPE, "application/json")
                          .header(ACCEPT, "application/json")
                          .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                          .build();
    }

    protected HttpRequest delete(URI uri) {
        return HttpRequest.newBuilder(uri)
                          .header(AUTH_TOKEN, this.credentials.getAccessToken())
                          .header(CONTENT_TYPE, "application/json")
                          .header(ACCEPT, "application/json")
                          .DELETE()
                          .build();
    }

    @SneakyThrows
    public <T> T getRequestWrapped(HttpRequest request, HttpResponse.BodyHandler<T> handler) {
        return client.sendAsync(request, handler)
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

    @SneakyThrows
    public String toString(Object object) {
        return objectMapper.writeValueAsString(object);
    }

    public BigcommerceOrders getOrderApi() {
        return new BigcommerceOrders(credentials);
    }
    
    public BigcommerceOrderProductsV2 getOrderProductsApi() {
        return new BigcommerceOrderProductsV2(credentials);
    }

    public BigcommerceStoreInformationV2 getStoreInfoApi() {
        return new BigcommerceStoreInformationV2(credentials);
    }

    public BigcommerceProducts getProductApi() {
        return new BigcommerceProducts(credentials);
    }

    public BigcommerceOrderShippingAddressesV2 getShippingAddressApi(){
        return new BigcommerceOrderShippingAddressesV2(credentials);
    }

    public BigcommerceWebhooks getWebhookApi(){
        return new BigcommerceWebhooks(credentials);
    }

    public BigcommerceUpdateInventory getUpdateInventoryApi(){
        return new BigcommerceUpdateInventory(credentials);
    }

    public BigcommerceProductsVariants getProductVariantApi(){
        return new BigcommerceProductsVariants(credentials);
    }

    public BigcommerceProductImages getProductImageApi(){
        return new BigcommerceProductImages(credentials);
    }

    public BigcommerceCustomer getCustomerApi() {
        return new BigcommerceCustomer(credentials);
    }

    public BigcommerceBrands getBrandsApi(){
        return new BigcommerceBrands(credentials);
    }

    public BigcommerceCategories getCategoriesApi(){
        return new BigcommerceCategories(credentials);
    }

    public BigcommerceCustomerAddresses getCustomerAddressApi(){
        return new BigcommerceCustomerAddresses(credentials);
    }
}