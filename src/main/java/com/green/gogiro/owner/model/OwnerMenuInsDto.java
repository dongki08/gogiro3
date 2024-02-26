package com.green.gogiro.owner.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OwnerMenuInsDto {
    @Schema(name = "메뉴이름")
    private String menu;
    @Schema(name = "메뉴가격")
    private Integer price;
}
