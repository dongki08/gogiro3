package com.green.gogiro.admin.model;

import lombok.Data;


@Data
public class ReportDto {
    private String table;
    private String column1;
    private String column2;
    private int page;
    private int startIdx;
    private int rowCount=10;
    public void setPage(int page){
        this.startIdx=(page-1)*this.rowCount;
    }
    public ReportDto(int check){
        switch(check){
            case 0:
                this.table="community";
                this.column1="board";
                this.column2="contents";
                break;
            case 1:
                this.table="community_comment";
                this.column1="comment";
                this.column2="contents";
                break;
            case 2:
                this.table="shop_review";
                this.column1="review";
                this.column2="review";
                break;
            case 3:
                this.table="but_review";
                this.column1="review";
                this.column2="review";
                break;
        }
    }
}
