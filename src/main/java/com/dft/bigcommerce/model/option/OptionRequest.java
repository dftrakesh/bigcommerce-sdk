package com.dft.bigcommerce.model.option;

import com.dft.bigcommerce.model.optionvalue.VariantOptionValue;
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
public class OptionRequest {

    private Integer id;
    private Integer productId;
    private String displayName;
    private String type;
    private Integer sortOrder;
    private List<VariantOptionValue> optionValues;
    private String image_url;
}