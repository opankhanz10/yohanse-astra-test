package com.hansen.astratest.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hansen.astratest.datasource.entity.Employees;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EmployeeDetailDto extends EmployeeDto{
        List<SalaryDto> salary;
        List<TitleDto> title;
        List<EmployeeRelationDetailDto> employeeDepartement;
        List<EmployeeRelationDetailDto> managerDepartement;

    public EmployeeDetailDto(Employees employees) {
        this.setEmpNo(employees.getEmpNo());
        this.setBirthDate(employees.getBirthDate());
        this.setFirstName(employees.getFirstName());
        this.setLastName(employees.getLastName());
        this.setGender(employees.getGender());
        this.setHireDate(employees.getHireDate());
    }
}
