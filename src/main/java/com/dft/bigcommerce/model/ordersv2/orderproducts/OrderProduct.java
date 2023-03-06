package com.dft.bigcommerce.model.ordersv2.orderproducts;

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
public class OrderProduct {

    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer variantId;
    private Integer orderPickupMethodId;
    private Integer orderAddressId;
    private String name;
    private String nameCustomer;
    private String nameMerchant;
    private String sku;
    private String upc;
    private String type;
    private String basePrice;
    private String priceExTax;
    private String priceIncTax;
    private String priceTax;
    private String baseTotal;
    private String totalExTax;
    private String totalIncTax;
    private String totalTax;
    private String weight;
    private String width;
    private String height;
    private String depth;
    private Integer quantity;
    private String baseCostPrice;
    private String costPriceIncTax;
    private String costPriceExTax;
    private String costPriceTax;
    private Boolean isRefunded;
    private Integer quantityRefunded;
    private String refundAmount;
    private Integer returnId;
    private Integer wrappingId;
    private String wrappingName;
    private String baseWrappingCost;
    private String wrappingCostExTax;
    private String wrappingCostIncTax;
    private String wrappingCostTax;
    private String wrappingMessage;
    private Integer quantityShipped;
    private Object eventName;
    private String eventDate;
    private String fixedShippingCost;
    private String ebayItemId;
    private String ebayTransactionId;
    private Integer optionSetId;
    private Object parentOrderProductId;
    private Boolean isBundledProduct;
    private String binPickingNumber;
    private Object externalId;
    private String fulfillmentSource;
    private String brand;
    private Object giftCertificateId;
    private List<AppliedDiscount> appliedDiscounts;
    private List<ProductOption> productOptions;
    private List<Object> configurableFields;
}