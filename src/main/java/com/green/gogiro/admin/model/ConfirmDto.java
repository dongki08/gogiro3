package com.green.gogiro.admin.model;

import lombok.Data;

@Data
public class ConfirmDto {
    private int checkShop;//가게 구분(0:고기집, 1:정육점)
    private int ishop;//가게 pk(최소 1 이상)
    private int confirm;//변경하고 싶은 승인 여부(0:대기, 1:확정, 2: 거절, 3:퇴출)
}
