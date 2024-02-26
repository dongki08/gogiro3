package com.green.gogiro.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ConfirmDto {
    private int checkShop;
    private int ireser;
    @JsonIgnore
    private boolean isReservation;
    public void setCheckShop(int checkShop){
        this.checkShop= checkShop;
        this.isReservation=(checkShop==0);
    }
}
