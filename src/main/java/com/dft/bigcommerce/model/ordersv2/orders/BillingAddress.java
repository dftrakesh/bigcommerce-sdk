package com.dft.bigcommerce.model.ordersv2.orders;

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
public class BillingAddress {

    private String firstName;
    private String lastName;
    private String company;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String countryIso2;
    private String phone;
    private String email;
    private List<FormField> formFields;
}