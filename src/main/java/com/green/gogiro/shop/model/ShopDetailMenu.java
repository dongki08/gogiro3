package com.green.gogiro.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopDetailMenu {
    @JsonIgnore
    private int ishop;
    @Schema(title = "가격")
    private int price;
    @Schema(title = "메뉴")
    private String menu;
    @Schema(title = "메뉴 사진")
    private String pic;
}
