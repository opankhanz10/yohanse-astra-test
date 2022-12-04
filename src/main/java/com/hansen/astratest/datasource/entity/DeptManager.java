package com.hansen.astratest.datasource.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(
        name = "dept_manager"
)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class DeptManager extends LongerDate {
    @EmbeddedId
    private DeptRelationIds deptManagerId;

    public DeptManager(DeptRelationIds deptManagerId, LocalDate fromDate, LocalDate toDate) {
        this.deptManagerId = deptManagerId;
        this.setFromDate(fromDate);
        this.setToDate(toDate);
    }
}
