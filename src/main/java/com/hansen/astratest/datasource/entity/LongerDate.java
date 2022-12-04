package com.hansen.astratest.datasource.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public abstract class LongerDate implements Serializable {
    @Column(
            name = "from_date"
    )
    private LocalDate fromDate;

    @Column(
            name = "to_date"
    )
    private LocalDate toDate;
}