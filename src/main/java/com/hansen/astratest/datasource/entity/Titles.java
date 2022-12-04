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
@Table(name = "titles")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
public class Titles {
    @EmbeddedId
    private TitlesIds titlesId;

    @Column(
            name = "to_date"
    )
    private LocalDate toDate;

    public Titles(TitlesIds titlesId, LocalDate toDate) {
        this.titlesId = titlesId;
        this.toDate = toDate;
    }
}
