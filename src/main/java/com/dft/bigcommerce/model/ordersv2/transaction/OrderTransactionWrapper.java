
package com.dft.bigcommerce.model.ordersv2.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderTransactionWrapper {

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("avs_result")
    private AvsResult avsResult;

    @JsonProperty("credit_card")
    private CreditCard creditCard;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("custom")
    private Object custom;

    @JsonProperty("cvv_result")
    private CvvResult cvvResult;

    @JsonProperty("date_created")
    private String dateCreated;

    @JsonProperty("event")
    private String event;

    @JsonProperty("fraud_review")
    private Boolean fraudReview;

    @JsonProperty("gateway")
    private String gateway;

    @JsonProperty("gateway_transaction_id")
    private String gatewayTransactionId;

    @JsonProperty("gift_certificate")
    private Object giftCertificate;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("method")
    private String method;

    @JsonProperty("offline")
    private Object offline;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("payment_instrument_token")
    private Object paymentInstrumentToken;

    @JsonProperty("payment_method_id")
    private String paymentMethodId;

    @JsonProperty("reference_transaction_id")
    private Object referenceTransactionId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("store_credit")
    private Object storeCredit;

    @JsonProperty("test")
    private Boolean test;
}
