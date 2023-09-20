package com.dft.bigcommerce.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BigcommerceWebhook extends CoreWebhookPayload {
    private WebhookData data;
}
