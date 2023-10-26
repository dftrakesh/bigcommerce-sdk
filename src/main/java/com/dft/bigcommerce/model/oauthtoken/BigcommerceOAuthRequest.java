package com.dft.bigcommerce.model.oauthtoken;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BigcommerceOAuthRequest {
    private String clientId;
    private String clientSecret;
    private String code;
    private String context;
    private String scope;
    private String grantType;
    private String redirectUri;
}