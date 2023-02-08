package com.dft.bigcommerce.model.catalog.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Image {

    private String imageFile;
    private Boolean isThumbnail;
    private Integer sortOrder;
    private String description;
    private String imageUrl;
    private Integer id;
    private Integer productId;
    private String urlZoom;
    private String urlStandard;
    private String urlThumbnail;
    private String urlTiny;
    private String dateModified;
}
