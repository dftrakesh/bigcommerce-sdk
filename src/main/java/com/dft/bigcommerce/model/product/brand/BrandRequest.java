package com.dft.bigcommerce.model.product.brand;

import com.dft.bigcommerce.model.product.CustomUrl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BrandRequest {

    private String name;
    private String pageTitle;
    private List<String> metaKeywords;
    private String metaDescription;
    private String searchKeywords;
    private String imageUrl;
    private CustomUrl customUrl;
}
