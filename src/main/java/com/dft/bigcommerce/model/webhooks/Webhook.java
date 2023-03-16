package com.dft.bigcommerce.model.webhooks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Webhook {

    private Integer id;
    private String clientId;
    private String storeHash;
    private String scope;
    private String destination;
    private Headers headers;
    private Boolean isActive;
    private Integer createdAt;
    private Integer updatedAt;
}