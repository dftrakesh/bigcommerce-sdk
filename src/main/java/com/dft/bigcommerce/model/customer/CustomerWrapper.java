package com.dft.bigcommerce.model.customer;

import com.dft.bigcommerce.model.common.Meta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerWrapper {

    private List<Customer> data;
    private Meta meta;
}
