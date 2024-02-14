package com.green.gogiro.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static com.green.gogiro.common.Const.REGEXP_DATE_TYPE5;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;


@Data
public class PickupInsDto {
    @JsonIgnore
    private int iuser;
    @Schema(title = "정육점pk")
    @Min(value = 1)
    private int ibutcher;
    @Schema(title = "예약날짜")
    @NotBlank
    @Pattern(regexp = REGEXP_DATE_TYPE5, message = "날짜 형식이 올바르지 않습니다")
    private String date;
    @Schema(title = "요청 사항")
    private String request;
    @Schema(title = "메뉴pk")
    @NotNull(message = "메뉴를 입력해주세요")
    private List<PickupMenuDto> menus;
    @JsonIgnore
    private int ipickup;
}
