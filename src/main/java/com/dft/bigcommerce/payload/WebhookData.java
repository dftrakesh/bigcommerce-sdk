package com.dft.bigcommerce.payload;

import lombok.Data;

@Data
public class WebhookData {
    private String type;
    private String id;
}
