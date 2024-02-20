
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
public class CreditCard {

    @JsonProperty("card_expiry_month")
    private Integer cardExpiryMonth;
    @JsonProperty("card_expiry_year")
    private Integer cardExpiryYear;
    @JsonProperty("card_iin")
    private String cardIin;
    @JsonProperty("card_last4")
    private String cardLast4;
    @JsonProperty("card_type")
    private String cardType;
}
