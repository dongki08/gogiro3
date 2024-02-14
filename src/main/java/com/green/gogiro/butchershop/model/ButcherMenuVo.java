package com.green.gogiro.butchershop.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ButcherMenuVo {
    @Schema(title= "메뉴pk")
    private int ibutMenu;
    @Schema(title= "메뉴 이름")
    private String menu;
}
