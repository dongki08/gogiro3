package com.green.gogiro.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ShopBookmarkDto {
    @JsonIgnore
    private int iuser;
    private int ishop;
    @JsonIgnore
    private boolean on;
}
