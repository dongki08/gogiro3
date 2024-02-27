package com.green.gogiro.reservation.model;

import lombok.Data;

@Data
public class PaymentDto {
    private int checkShop;//가게 구분
    private int ireser;//예약 pk
    private int amount;//결제 총 금액
    private boolean isReservation;
    public void setCheckShop(int checkShop){
        this.checkShop=checkShop;
        this.isReservation=(checkShop==0);
    }
}
