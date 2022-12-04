package com.hansen.astratest.datasource.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hansen.astratest.utils.GenderEnums;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "employees"
)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "emp_no",
            columnDefinition = "numeric(11)",
            length = 11
    )
    @JsonIgnore
    private Integer empNo;

    @Column(
            name = "birth_date"
    )
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd"
    )
    private LocalDate birthDate;

    @Column(
            name = "first_name",
            columnDefinition = "varchar(14)",
            length = 14
    )
    private String firstName;

    @Column(
            name = "last_name",
            columnDefinition = "varchar(16)",
            length = 16
    )
    private String lastName;

    @Column(
            name = "gender",
            columnDefinition = "character(1)",
            length = 1
    )
    @Enumerated(EnumType.STRING)
    private GenderEnums gender;

    @Column(
            name = "hire_date"
    )
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd"
    )
    private LocalDate hireDate;
}

