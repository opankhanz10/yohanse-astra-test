package com.hansen.astratest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hansen.astratest.utils.Constant;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TitleDto{
    @NotNull(message = Constant.TITLE_NULL_MESSAGE)
    @NotBlank(message = Constant.TITLE_NULL_MESSAGE)
    @Size(max=50, message = "Title too long")
    private String title;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd"
    )
    @NotNull(message = Constant.TITLE_FROM_DATE_NULL_MESSAGE)
    private LocalDate fromDate;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd"
    )
    @NotNull(message = Constant.TITLE_TO_DATE_NULL_MESSAGE)
    private LocalDate toDate;

    public TitleDto(String title,LocalDate fromDate, LocalDate toDate) {
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}
