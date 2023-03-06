package com.dft.bigcommerce.model.option;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Config {

    private String defaultValue;
    private Boolean checkedByDefault;
    private String checkboxLabel;
    private Boolean dateLimited;
}