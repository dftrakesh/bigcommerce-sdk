package com.dft.bigcommerce.model.product.brand;

import com.dft.bigcommerce.model.common.Meta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandsWrapper {

    private List<Brand> data;
    private Meta meta;
}
