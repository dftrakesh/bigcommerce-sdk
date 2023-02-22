package com.dft.bigcommerce.model.product.brandMetafields;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BrandMetafieldRequest {

    private Integer id;
    private String key;
    private String value;
    private String namespace;
    private String permissionSet;
    private String resourceType;
    private Integer resourceId;
    private String description;
    private String dateCreated;
    private String dateModified;
}