package com.dft.bigcommerce.model.product.variant;

import com.dft.bigcommerce.model.common.Meta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VariantsWrapper {

    private List<Variant> data;
    private Meta meta;
}