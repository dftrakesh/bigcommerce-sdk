package com.dft.bigcommerce.model.ordersv2.ordershipments;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderShipmentRequest {

    private Integer orderAddressId;
    private String trackingNumber;
    private String shippingMethod;
    private String shippingProvider;
    private String trackingCarrier;
    private List<OrderShipmentItems> items;
}