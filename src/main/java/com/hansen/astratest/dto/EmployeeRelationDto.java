package com.hansen.astratest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hansen.astratest.utils.Constant;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EmployeeRelationDto{
    @NotNull(message = Constant.DEPARTEMENT_NUMBER_NULL_MESSAGE)
    @NotBlank(message = Constant.DEPARTEMENT_NUMBER_NULL_MESSAGE)
    private String deptNo;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd"
    )
    @NotNull(message = Constant.EMPLOYEE_RELATION_FROM_DATE_NULL_MESSAGE)
    private LocalDate fromDate;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd"
    )
    @NotNull(message = Constant.EMPLOYEE_RELATION_TO_DATE_NULL_MESSAGE)
    private LocalDate toDate;
}
