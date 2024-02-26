package com.green.gogiro.openapi.model;

import lombok.Data;

import java.util.List;

@Data
public class BuisnessVo {
    private String status_code;
    private Integer match_cnt;
    private List<BuisnessData> data;
}
