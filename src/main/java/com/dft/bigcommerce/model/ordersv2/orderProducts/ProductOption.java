package com.dft.bigcommerce.model.ordersv2.orderProducts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductOption {

    private Integer id;
    private Integer optionId;
    private Integer orderProductId;
    private Integer productOptionId;
    private String displayName;
    private String displayNameCustomer;
    private String displayNameMerchant;
    private String displayValue;
    private String displayValueCustomer;
    private String displayValueMerchant;
    private String value;
    private String type;
    private String name;
    private String displayStyle;
}