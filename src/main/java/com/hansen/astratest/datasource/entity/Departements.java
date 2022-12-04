package com.hansen.astratest.datasource.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
        name = "departements"
)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
public class Departements {
    @Id
    @Column(
            name = "dept_no",
            columnDefinition = "character(4)",
            length = 4
    )
    private String deptNo;

    @Column(
            name = "dept_name",
            columnDefinition = "varchar(40)",
            length = 40
    )
    private String deptName;
}
