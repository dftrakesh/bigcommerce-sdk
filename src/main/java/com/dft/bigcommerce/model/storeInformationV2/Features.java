package com.dft.bigcommerce.model.storeinformationv2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Features {

    private Boolean stencilEnabled;
    private Boolean sitewidehttpsEnabled;
    private String facebookCatalogId;
    private String checkoutType;
    private Boolean wishlistsEnabled;
    private Boolean graphqlStorefrontApiEnabled;
    private Boolean shopperConsentTrackingEnabled;
    private Boolean multiStorefrontEnabled;
}