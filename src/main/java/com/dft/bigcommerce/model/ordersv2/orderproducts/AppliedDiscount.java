package com.dft.bigcommerce.model.ordersv2.orderproducts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppliedDiscount {

    private String id;
    private String amount;
    private String name;
    private Object code;
    private String target;
}