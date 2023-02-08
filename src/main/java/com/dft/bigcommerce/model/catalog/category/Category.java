package com.dft.bigcommerce.model.catalog.category;

import com.dft.bigcommerce.model.common.CustomUrl;
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
public class Category {

    private Integer id;
    private Integer parentId;
    private String name;
    private String description;
    private Integer views;
    private Integer sortOrder;
    private String pageTitle;
    private List<String> metaKeywords = new ArrayList<>();
    private String metaDescription;
    private String layoutFile;
    private String imageUrl;
    private Boolean isVisible;
    private String searchKeywords;
    private String defaultProductSort;
    private CustomUrl customUrl;

}
