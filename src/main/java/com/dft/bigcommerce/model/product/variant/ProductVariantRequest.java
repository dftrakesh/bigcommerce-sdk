package com.dft.bigcommerce.model.product.variant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductVariantRequest {

    private Integer productId;
    private String sku;
    private Double price;
    private Double salePrice;
    private Double retailPrice;
    private Double weight;
    private Double width;
    private Double height;
    private Double depth;
    private Boolean isFreeShipping;
    private Double fixedCostShippingPrice;
    private Boolean purchasingDisabled;
    private String purchasingDisabledMessage;
    private Double costPrice;
    private String upc;
    private String mpn;
    private String gtin;
    private Integer inventoryLevel;
    private Integer inventoryWarningLevel;
    private String binPickingNumber;
}