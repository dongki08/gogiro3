package com.green.gogiro.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.gogiro.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ReservationUpdDto {
    @JsonIgnore
    private int iuser;

    @Schema(title = "가게 구분 0:고기 1:정육")
    @Min(value = 0)
    @Max(value = 1)
    private int checkShop;
    @Schema(title = "예약pk")
    @Min(value = 1)
    private int ireser;
    @Schema(title = "예약날짜",example = "0000-00-00 00:00:00")
    @Pattern(regexp= Const.REGEXP_DATE_TYPE5, message= "날짜 형식이 올바르지 않습니다")
    @NotBlank(message = "예약날짜를 입력해 주세요.")
    private String date;
    @Schema(title = "요청 사항")
    private String request;
    @Schema(title = "인원수",defaultValue = "1")
    @Min(value = 1)
    private int headCount;
}
