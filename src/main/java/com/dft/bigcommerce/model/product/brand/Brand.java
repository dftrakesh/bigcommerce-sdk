package com.dft.bigcommerce.model.product.brand;

import com.dft.bigcommerce.model.product.CustomUrl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Brand {

    private Integer id;
    private String name;
    private String pageTitle;
    private List<String> metaKeywords = new ArrayList<>();
    private String metaDescription;
    private String searchKeywords;
    private String imageUrl;
    private CustomUrl customUrl;

}
