package com.hansen.astratest.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EmployeeRelationDetailDto extends EmployeeRelationDto{
     String deptName;

    public EmployeeRelationDetailDto(String deptNo, String deptName, LocalDate fromDate, LocalDate toDate) {
        this.deptName = deptName;
        this.setDeptNo(deptNo);
        this.setFromDate(fromDate);
        this.setToDate(toDate);
    }
}
