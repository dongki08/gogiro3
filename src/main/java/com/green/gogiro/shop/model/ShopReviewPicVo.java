package com.green.gogiro.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ShopReviewPicVo {
    @JsonIgnore
    private int ireview;

    private String pic;
}
