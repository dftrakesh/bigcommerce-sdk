package com.dft.bigcommerce.model.product.variantimage;

import com.dft.bigcommerce.model.common.Meta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VariantImageWrapper {

    private VariantImage data;
    private Meta meta;
}