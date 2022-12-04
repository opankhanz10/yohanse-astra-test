package com.hansen.astratest.datasource.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(
        name = "salaries"
)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
public class Salaries {
    @EmbeddedId
    private SalariesIds salariesId;

    @Column(
            name = "salary",
            columnDefinition = "numeric(11)",
            length = 0
    )
    private Integer salary;

    @Column(
            name = "to_date"
    )
    private LocalDate toDate;

    public Salaries(SalariesIds salariesId, Integer salary, LocalDate toDate) {
        this.salariesId = salariesId;
        this.salary = salary;
        this.toDate = toDate;
    }
}
