package com.green.gogiro.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.gogiro.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static java.lang.Math.abs;

@Data
public class ReservationInsDto {
    @JsonIgnore
    private int ireser;
    @JsonIgnore
    private int iuser;
    @Schema(title = "고기집pk")
    @Min(value = 1)
    private int ishop;
    @Schema(title = "예약날짜")
    @NotBlank
    @Pattern(regexp= Const.REGEXP_DATE_TYPE5, message= "날짜 형식이 올바르지 않습니다")
    private String date;
    @Schema(title = "요청 사항")
    @Nullable
    private String request;
    @Schema(title = "인원수",defaultValue = "1")
    @Min(value = 1)
    private int headCount;
    @JsonIgnore
    private LocalDateTime localDateTime;
    @JsonIgnore
    private boolean invalidDate;
    public void setDate(String date){
        this.date= date;
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        this.localDateTime= LocalDateTime.parse(date+".000", formatter);
        LocalDate dayDate1 = LocalDateTime.now().toLocalDate();
        LocalDate dayDate2 = this.localDateTime.toLocalDate();
        Period diff= Period.between(dayDate1, dayDate2);
        int year= abs(diff.getYears());
        int month= abs(diff.getMonths());
        int day= abs(diff.getDays());
        this.invalidDate=(year>0||month>0||day>=30);
    }
}
