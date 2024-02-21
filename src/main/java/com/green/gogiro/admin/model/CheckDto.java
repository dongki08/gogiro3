package com.green.gogiro.admin.model;

import lombok.Data;

@Data
public class CheckDto {
    private int iuser;
    private int check;
    private boolean isCheck;

    public CheckDto(int iuser){this.iuser= iuser;}
    public void setCheck(int check){
        this.isCheck= (check==0);
    }

}
