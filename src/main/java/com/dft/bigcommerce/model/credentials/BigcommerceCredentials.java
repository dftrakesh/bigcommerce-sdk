package com.dft.bigcommerce.model.credentials;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BigcommerceCredentials {
    private String storeHash;
    private String accessToken;
}