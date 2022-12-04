package com.hansen.astratest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hansen.astratest.utils.Constant;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SalaryDto {
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd"
    )
    @NotNull(message = Constant.SALARY_FROM_DATE_NULL_MESSAGE)
    private LocalDate fromDate;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd"
    )
    @NotNull(message = Constant.SALARY_TO_DATE_NULL_MESSAGE)
    private LocalDate toDate;

    @NotNull(message = Constant.SALARY_VALUE_NULL_MESSAGE)
    private Integer salary;

    public SalaryDto(LocalDate fromDate, LocalDate toDate,Integer salary) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.salary = salary;
    }
}
