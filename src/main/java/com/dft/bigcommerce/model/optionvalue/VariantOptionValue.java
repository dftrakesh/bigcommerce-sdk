package com.dft.bigcommerce.model.optionvalue;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VariantOptionValue {

    private Integer id;
    private String label;
    private Integer sortOrder;
    private String valueData;
    private Boolean isDefault;
}