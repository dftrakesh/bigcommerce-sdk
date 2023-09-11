package com.dft.bigcommerce.payload;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CoreWebhookPayload {
    public String scope;
    public String storeId;
    public String hash;
    public String createdAt;
    public String producer;
}
