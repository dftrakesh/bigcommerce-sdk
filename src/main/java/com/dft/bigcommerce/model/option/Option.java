package com.dft.bigcommerce.model.option;

import com.dft.bigcommerce.model.optionvalue.VariantOptionValue;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Option {

    private Integer id;
    private Integer productId;
    private String name;
    private String displayName;
    private String type;
    private Integer sortOrder;
    private List<Config> config;
    private List<VariantOptionValue> optionValues;
}