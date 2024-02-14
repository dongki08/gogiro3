package com.green.gogiro.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.gogiro.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

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
    @Pattern(regexp= Const.REGEXP_DATE_TYPE5, message= "날짜 형식이 올바르지 않습니다")
    private String date;
    @Schema(title = "요청 사항")
    @Nullable
    private String request;
    @Schema(title = "인원수",defaultValue = "1")
    @Min(value = 1)
    private int headCount;
}
