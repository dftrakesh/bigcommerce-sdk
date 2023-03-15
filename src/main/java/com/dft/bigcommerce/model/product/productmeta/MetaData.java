package com.dft.bigcommerce.model.product.productmeta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaData {

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
