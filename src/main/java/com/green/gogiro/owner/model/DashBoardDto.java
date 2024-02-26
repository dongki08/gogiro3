package com.green.gogiro.owner.model;

import lombok.Data;

@Data
public class DashBoardDto {
    private int checkShop;
    private Long ishop;
    private String table;
    private String table2;
    private String column;
    public void setCheckShop(int checkShop){
        this.checkShop= checkShop;
        this.table= (checkShop==0)?"shop":"but";
        this.table2= (checkShop==0)?"reservation":"pickup";
        this.column= (checkShop==0)?"shop":"butcher";

    }
}
