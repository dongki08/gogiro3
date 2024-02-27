package com.green.gogiro.owner.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OwnerMenuUpdDto {
    @Schema(description = "메뉴pk")
    private Long imenu;
    @Schema(description = "메뉴이름")
    private String menu;
    @Schema(description = "메뉴가격")
    private Integer price;
}
