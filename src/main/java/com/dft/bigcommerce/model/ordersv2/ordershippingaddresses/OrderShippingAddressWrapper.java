package com.dft.bigcommerce.model.ordersv2.ordershippingaddresses;

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
public class OrderShippingAddressWrapper {

    private Integer id;
    private Integer orderId;
    private String firstName;
    private String lastName;
    private String company;
    private String street1;
    private String street2;
    private String city;
    private String zip;
    private String country;
    private String countryIso2;
    private String state;
    private String email;
    private String phone;
    private Integer itemsTotal;
    private Integer itemsShipped;
    private String shippingMethod;
    private String baseCost;
    private String costExTax;
    private String costIncTax;
    private String costTax;
    private Integer costTaxClassId;
    private String baseHandlingCost;
    private String handlingCostExTax;
    private String handlingCostIncTax;
    private String handlingCostTax;
    private Integer handlingCostTaxClassId;
    private Integer shippingZoneId;
    private String shippingZoneName;
    private ShippingQuotes shippingQuotes;
    private List<Object> formFields;
}