package com.hansen.astratest.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NoArgsConstructor
public class SalariesIds implements Serializable {
    @Column(
            name = "emp_no",
            columnDefinition = "numeric(11)",
            length = 11
    )
    @JsonIgnore
    private Integer empNo;

    @Column(
            name = "from_date"
    )
    private LocalDate fromDate;

    public SalariesIds(Integer empNo, LocalDate fromDate) {
        this.empNo = empNo;
        this.fromDate = fromDate;
    }
}
