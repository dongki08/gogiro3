package com.green.gogiro.admin.model;

import lombok.Data;

@Data
public class ShopDto {
    private int page;
    private String search;
    private int startIdx;
    private int rowCount=10;
    public void setPage(int page){
        this.startIdx=(page-1)*this.rowCount;
    }
}
