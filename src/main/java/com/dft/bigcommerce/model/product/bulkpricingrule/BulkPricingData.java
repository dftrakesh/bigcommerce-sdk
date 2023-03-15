package com.dft.bigcommerce.model.product.bulkpricingrule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BulkPricingData {

    private Long id;
    private Long quantityMin;
    private Long quantityMax;
    private String type;
    private Double amount;
}
