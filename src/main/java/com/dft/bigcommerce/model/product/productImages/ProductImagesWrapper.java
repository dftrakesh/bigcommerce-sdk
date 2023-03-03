package com.dft.bigcommerce.model.product.productImages;

import com.dft.bigcommerce.model.common.Meta;
import com.dft.bigcommerce.model.product.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductImagesWrapper {

    private List<Image> data;
    private Meta meta;
}