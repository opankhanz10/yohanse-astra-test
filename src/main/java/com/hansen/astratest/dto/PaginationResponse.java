package com.hansen.astratest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PaginationResponse {
    private Object data;
    private Long totalData;
    private Integer totalPages;
    private Integer currentPage;
    private Integer sizeOfData;
    private String dataOf;
}
