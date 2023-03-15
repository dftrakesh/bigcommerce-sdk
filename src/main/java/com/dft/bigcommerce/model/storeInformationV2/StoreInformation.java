package com.dft.bigcommerce.model.storeinformationv2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreInformation {

    private String id;
    private String accountUuid;
    private String domain;
    private String secureUrl;
    private String controlPanelBaseUrl;
    private String status;
    private String name;
    private String firstName;
    private String lastName;
    private String address;
    private String country;
    private String countryCode;
    private String phone;
    private String adminEmail;
    private String orderEmail;
    private String faviconUrl;
    private Timezone timezone;
    private String language;
    private String currency;
    private String currencySymbol;
    private String decimalSeparator;
    private String thousandsSeparator;
    private Integer decimalPlaces;
    private String currencySymbolLocation;
    private String weightUnits;
    private String dimensionUnits;
    private Integer dimensionDecimalPlaces;
    private String dimensionDecimalToken;
    private String dimensionThousandsToken;
    private String planName;
    private String planLevel;
    private Boolean planIsTrial;
    private String industry;
    private List<Object> logo;
    private Boolean isPriceEnteredWithTax;
    private Integer storeId;
    private Integer defaultSiteId;
    private Integer defaultChannelId;
    private Features features;
}