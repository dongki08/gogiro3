package com.green.gogiro.butchershop.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ButcherSelVo {
    @Schema(title = "정육점pk")
    private int ibutcher;
    @Schema(title = "상호명")
    private String name;
    @Schema(title = "위치")
    private String location;
    @Schema(title = "")
    private int count;
    @Schema(title = "사진")
    private List<String> pics = new ArrayList<>();
    @Schema(title = "메뉴 pk&이름 리스트")
    private List<ButcherMenuVo> menuList;
}
