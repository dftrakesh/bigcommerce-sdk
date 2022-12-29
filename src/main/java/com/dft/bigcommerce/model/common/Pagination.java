package com.dft.bigcommerce.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination {

    private Integer total;
    private Integer count;
    private Integer perPage;
    private Integer currentPage;
    private Integer totalPages;
    private Links links;
    private Boolean tooMany;
}
