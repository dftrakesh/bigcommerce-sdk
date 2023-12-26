package com.dft.bigcommerce.model.ordersv2.shipment;

import com.dft.bigcommerce.model.ordersv2.orders.BillingAddress;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderShipmentWrapper {

    private Integer id;
    private Integer orderId;
    private Integer customerId;
    private Integer orderAddressId;
    private String dateCreated;
    private String trackingNumber;
    private String merchantShippingCost;
    private String comments;
    private String shippingProvider;
    private String trackingCarrier;
    private String trackingLink;
    private BillingAddress billingAddress;
    private BillingAddress shippingAddress;
    private List<OrderItemShipment> items;
    private String shippingProviderDisplayName;
    private String generatedTrackingLink;
}