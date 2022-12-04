package com.hansen.astratest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hansen.astratest.utils.Constant;
import com.hansen.astratest.utils.GenderEnums;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestEmployeeDto {
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd"
    )
    @NotNull(message = Constant.BIRTH_DATE_NULL_MESSAGE)
    private LocalDate birthDate;

    @NotNull(message = Constant.FIRST_NAME_NULL_MESSAGE)
    @NotEmpty(message = Constant.FIRST_NAME_NULL_MESSAGE)
    @Size(max = 14, message = Constant.FIRST_NAME_TO_LONG_MESSAGE)
    private String firstName;

    @Size(max = 16, message = Constant.LAST_NAME_TO_LONG_MESSAGE)
    private String lastName;

    @NotNull(message = Constant.GENDER_NULL_MESSAGE)
    private GenderEnums gender;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd"
    )
    @NotNull(message = Constant.HIRING_DATE_NULL_MESSAGE)
    private LocalDate hireDate;

    @Valid
    private List<SalaryDto> salary;

    @Valid
    private List<EmployeeRelationDto> employeeDepartement;

    private List<EmployeeRelationDto> managerDepartement;

    @Valid
    private List<TitleDto> title;

}
