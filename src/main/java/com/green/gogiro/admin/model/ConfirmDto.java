package com.green.gogiro.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ConfirmDto {
    @Min(value=0,message="가게 구분 값이 유효하지 않습니다")
    @Max(value=1,message="가게 구분 값이 유효하지 않습니다")
    private int checkShop;//가게 구분(0:고기집, 1:정육점)

    @Min(value=1,message="가게pk는 최소 1 이상이어야 합니다")
    private int ishop;//가게 pk(최소 1 이상)

    @Min(value=0,message="승인 여부 값이 올바르지 않습니다")
    @Max(value=3,message="승인 여부 값이 올바르지 않습니다")
    private int confirm;//변경하고 싶은 승인 여부(0:대기, 1:확정, 2: 거절, 3:퇴출)

    @JsonIgnore
    private String table;
    @JsonIgnore
    private String column;
    @JsonIgnore
    private boolean isShop;
    public void setCheckShop(int checkShop) {
        this.isShop=(checkShop==0);
        this.table =this.isShop? "shop": "butcher_shop";
        this.column =this.isShop? "shop": "butcher";
    }
}
