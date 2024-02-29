package com.green.gogiro.admin.model;

import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
public class BlackDto {
    private int page;
    private List<Integer> list;
    private int startIdx;
    private int rowCount=10;
    public void setPage(int page){
        this.startIdx=(page-1)*this.rowCount;
    }
}
