package com.dft.bigcommerce;

import com.dft.bigcommerce.handler.JsonBodyHandler;
import com.dft.bigcommerce.model.oauthtoken.BigcommerceOAuthRequest;
import com.dft.bigcommerce.model.oauthtoken.BigcommerceOAuthResponse;
import java.net.URI;
import java.net.http.HttpRequest;

public class BigcommerceOAuthTokenAPI extends BigcommerceSDK {

    private static final String OAUTH_TWO_TOKEN_END_POINT = "https://login.bigcommerce.com/oauth2/token";

    public BigcommerceOAuthTokenAPI() {
        super();
    }

    public BigcommerceOAuthResponse getAccessToken(BigcommerceOAuthRequest bigcommerceOAuthRequest) {
        URI uri = URI.create(OAUTH_TWO_TOKEN_END_POINT);

        String jsonBody = toString(bigcommerceOAuthRequest);
        HttpRequest request = postWithOutAccessToken(uri, jsonBody);
        JsonBodyHandler<BigcommerceOAuthResponse> handler = new JsonBodyHandler<>(BigcommerceOAuthResponse.class);
        return getRequestWrapped(request, handler);
    }
}