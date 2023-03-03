package com.dft.bigcommerce.model.product.productImages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductImageRequest {

    private Integer id;
    private Integer productId;
    private Boolean isThumbnail;
    private Integer sortOrder;
    private String description;
    private String imageFile;
    private String imageUrl;
    private String urlZoom;
    private String urlStandard;
    private String urlThumbnail;
    private String urlTiny;
    private String dateModified;
}