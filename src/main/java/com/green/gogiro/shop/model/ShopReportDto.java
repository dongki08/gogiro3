package com.green.gogiro.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ShopReportDto {
    private int ireview;

    @JsonIgnore
    private int iuser;

    private int ireport;
}
