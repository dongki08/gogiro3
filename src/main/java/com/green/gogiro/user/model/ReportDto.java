package com.green.gogiro.user.model;

import lombok.Data;

@Data
public class ReportDto {
    private Long ireview;
    private Long ireport;
    private int checkShop; // 0 : 고깃집, 1 : 정육점
}
