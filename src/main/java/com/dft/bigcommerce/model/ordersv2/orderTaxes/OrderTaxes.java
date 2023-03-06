package com.dft.bigcommerce.model.ordersv2.orderTaxes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderTaxes {

    private Integer id;
    private Integer orderId;
    private Integer orderAddressId;
    private Integer taxRateId;
    private Integer taxClassId;
    private String name;
    private String _class;
    private String rate;
    private Integer priority;
    private String priorityAmount;
    private String lineAmount;
}