package com.dft.bigcommerce.model.option;

import com.dft.bigcommerce.model.common.Meta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VariantOptionValueWrapper {

    private VariantOptionValue data;
    private Integer status;
    private String title;
    private String type;
    private Meta meta;
}