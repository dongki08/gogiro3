package com.green.gogiro.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PaymentDto {
    @Min(value = 0,message = "가게 구분 값이 잘못되었습니다")
    @Max(value = 1,message = "가게 구분 값이 잘못되었습니다")
    private int checkShop;//가게 구분
    @Min(value = 1,message = "pk값이 잘못되었습니다")
    private int ireser;//예약 pk
    @Min(value = 0,message = "금액이 잘못 입력되었습니다")
    private int amount;//결제 총 금액
    @JsonIgnore
    private boolean isReservation;
    public void setCheckShop(int checkShop){
        this.checkShop=checkShop;
        this.isReservation=(checkShop==0);
    }
}
