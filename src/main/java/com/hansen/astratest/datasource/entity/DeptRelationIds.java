package com.hansen.astratest.datasource.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NoArgsConstructor
public class DeptRelationIds implements Serializable {
    @Column(
            name = "dept_no",
            columnDefinition = "character(4)",
            length = 4
    )
    private String deptNo;

    @Column(
            name = "emp_no",
            columnDefinition = "numeric(11)",
            length = 11
    )
    private Integer empNo;

    public DeptRelationIds(String deptNo, Integer empNo) {
        this.deptNo = deptNo;
        this.empNo = empNo;
    }
}
