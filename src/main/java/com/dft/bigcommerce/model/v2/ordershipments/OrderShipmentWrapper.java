package com.dft.bigcommerce.model.v2.ordershipments;

import com.dft.bigcommerce.model.ordersv2.orders.BillingAddress;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderShipmentWrapper {
    private Integer id;
    private Integer orderId;
    private Integer customerId;
    private Integer orderAddressId;
    private String dateCreated;
    private String trackingNumber;
    private String merchantShippingCost;
    private String shippingMethod;
    private String comments;
    private String shippingProvider;
    private String trackingCarrier;
    private String trackingLink;
    private BillingAddress billingAddress;
    private BillingAddress shippingAddress;
    private List<OrderShipmentItems> items;
    private String shippingProviderDisplayName;
    private String generatedTrackingLink;
}