package com.green.gogiro.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CancelDto {
    @JsonIgnore
    private int iuser;
    @Schema(title= "가게구분(0:고기집/1:정육점)")
    @Min(value = 0, message = "가게구분 값이 잘못되었습니다")
    @Max(value = 1, message = "가게구분 값이 잘못되었습니다")
    private int checkShop;
    @Schema(title = "예약pk")
    @Min(value = 0, message = "예약pk가 없습니다")
    private int ireser;
    @JsonIgnore
    private boolean isReservation;
    public void setCheckShop(int checkShop) {
        this.isReservation= (checkShop==0);
        this.checkShop= checkShop;
    }
}