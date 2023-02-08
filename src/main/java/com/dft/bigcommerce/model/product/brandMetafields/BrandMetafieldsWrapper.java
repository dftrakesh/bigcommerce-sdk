package com.dft.bigcommerce.model.product.brandMetafields;

import com.dft.bigcommerce.model.common.Meta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrandMetafieldsWrapper {

    private List<BrandMetafield> data;
    private Meta meta;
}
