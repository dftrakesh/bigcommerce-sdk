package com.dft.bigcommerce.model.product.brandMetafields;

import com.dft.bigcommerce.model.common.Meta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrandMetafieldWrapper {

    private BrandMetafield data;
    private Meta meta;
}
