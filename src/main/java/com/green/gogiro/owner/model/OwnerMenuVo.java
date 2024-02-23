package com.green.gogiro.owner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerMenuVo {
    private int checkShop;
    private Long ishop;
    @Schema(title = "가격")
    private int price;
    @Schema(title = "메뉴")
    private String menu;
    @Schema(title = "메뉴 사진")
    private String pic;
}
