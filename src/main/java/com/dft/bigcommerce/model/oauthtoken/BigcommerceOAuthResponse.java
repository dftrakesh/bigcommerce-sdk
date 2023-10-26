package com.dft.bigcommerce.model.oauthtoken;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BigcommerceOAuthResponse {
    private String accessToken;
    private String scope;
    private User user;
    private String context;
    private String accountUuid;
}