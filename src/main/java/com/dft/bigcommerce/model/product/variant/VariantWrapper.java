package com.dft.bigcommerce.model.product.variant;

import com.dft.bigcommerce.model.common.Meta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VariantWrapper {

    private Variant data;
    private Meta meta;
    private Integer status;
    private String title;
    private String type;
    private String errors;
}