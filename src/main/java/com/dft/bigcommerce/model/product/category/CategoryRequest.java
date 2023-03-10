package com.dft.bigcommerce.model.product.category;

import com.dft.bigcommerce.model.product.CustomUrl;
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
public class CategoryRequest {

    private Integer parentId;
    private String name;
    private String description;
    private Integer views;
    private Integer sortOrder;
    private String pageTitle;
    private String searchKeywords;
    private List<String> metaKeywords;
    private String metaDescription;
    private String layoutFile;
    private Boolean isVisible;
    private String defaultProductSort;
    private String imageUrl;
    private CustomUrl customUrl;

}
