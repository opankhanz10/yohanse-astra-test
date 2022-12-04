package com.hansen.astratest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hansen.astratest.utils.GenderEnums;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {

    private Integer empNo;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd"
    )
    private LocalDate birthDate;

    private String firstName;

    private String lastName;

    private GenderEnums gender;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd"
    )
    private LocalDate hireDate;

    @JsonIgnore
    private LocalDate birthDateFrom;

    @JsonIgnore
    private LocalDate birthDateTo;

    @JsonIgnore
    private LocalDate hireDateFrom;

    @JsonIgnore
    private LocalDate hirehDateTo;

    @JsonIgnore
    private String search;

    public EmployeeDto(Integer empNo, LocalDate birthDate, String firstName, String lastName, GenderEnums gender, LocalDate hireDate) {
        this.empNo = empNo;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
    }

    public EmployeeDto(String search,LocalDate birthDateFrom, LocalDate birthDateTo, LocalDate hireDateFrom, LocalDate hirehDateTo) {
        this.birthDateFrom = birthDateFrom;
        this.birthDateTo = birthDateTo;
        this.hireDateFrom = hireDateFrom;
        this.hirehDateTo = hirehDateTo;
        this.search = search;
    }
}
