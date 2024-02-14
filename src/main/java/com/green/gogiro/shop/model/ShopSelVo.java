package com.green.gogiro.shop.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShopSelVo {
    @Schema(title = "고기집pk")
    private int ishop;
    @Schema(title = "상호명")
    private String name;
    @Schema(title = "위치")
    private String location;
    private int count;
    @Schema(title = "사진")
    private List<String> pics = new ArrayList<>();
    private List<String> facilities = new ArrayList<>();

}
