package com.dft.bigcommerce.model.product.category;

import com.dft.bigcommerce.model.common.Meta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriesWrapper {

    private List<Category> data;
    private Meta meta;
}
