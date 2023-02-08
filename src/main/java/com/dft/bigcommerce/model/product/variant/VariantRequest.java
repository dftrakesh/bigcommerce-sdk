package com.dft.bigcommerce.model.product.variant;

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
public class VariantRequest {

    private String sku;
    private Integer inventoryLevel;
    private Integer inventoryWarningLevel;
    private String imageUrl;
    private Double price;
    private Double weight;
    private List<OptionalValueRequest> optionValues;
}