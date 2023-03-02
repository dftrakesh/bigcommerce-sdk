package com.dft.bigcommerce.model.ordersv2.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderWrapper {

    private Integer id;
    private Integer customerId;
    private String dateCreated;
    private String dateModified;
    private String dateShipped;
    private Integer statusId;
    private String status;
    private String subtotalExTax;
    private String subtotalIncTax;
    private String subtotalTax;
    private String baseShippingCost;
    private String shippingCostExTax;
    private String shippingCostIncTax;
    private String shippingCostTax;
    private Integer shippingCostTaxClassId;
    private String baseHandlingCost;
    private String handlingCostExTax;
    private String handlingCostIncTax;
    private String handlingCostTax;
    private Integer handlingCostTaxClassId;
    private String baseWrappingCost;
    private String wrappingCostExTax;
    private String wrappingCostIncTax;
    private String wrappingCostTax;
    private Integer wrappingCostTaxClassId;
    private String totalExTax;
    private String totalIncTax;
    private String totalTax;
    private Integer itemsTotal;
    private Integer itemsShipped;
    private String paymentMethod;
    private Object paymentProviderId;
    private String paymentStatus;
    private String refundedAmount;
    private Boolean orderIsDigital;
    private String storeCreditAmount;
    private String giftCertificateAmount;
    private String ipAddress;
    private String ipAddressV6;
    private String geoipCountry;
    private String geoipCountryIso2;
    private Integer currencyId;
    private String currencyCode;
    private String currencyExchangeRate;
    private Integer defaultCurrencyId;
    private String defaultCurrencyCode;
    private String staffNotes;
    private String customerMessage;
    private String discountAmount;
    private String couponDiscount;
    private Integer shippingAddressCount;
    private Boolean isDeleted;
    private String ebayOrderId;
    private String cartId;
    private BillingAddress billingAddress;
    private Boolean isEmailOptIn;
    private Object creditCardType;
    private String orderSource;
    private Integer channelId;
    private Object externalSource;
    private Products products;
    private ShippingAddresses shippingAddresses;
    private Coupons coupons;
    private Object externalId;
    private ExternalMerchantId externalMerchantId;
    private String taxProviderId;
    private String customerLocale;
    private String externalOrderId;
    private String storeDefaultCurrencyCode;
    private String storeDefaultToTransactionalExchangeRate;
    private String customStatus;
}