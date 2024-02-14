package com.green.gogiro.butchershop.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ButcherShopDetailVo {
    @Schema(title = "정육점pk")
    private int ibutcher;
    @Schema(title = "상호명")
    private String name;
    @Schema(title = "위치")
    private String location;
    @Schema(title = "상세정보")
    private String open;
    private String tel;
    @Schema(title = "경도")
    private String x;
    @Schema(title = "위도")
    private String y;
    @Schema(title = "별점")
    private double star;
    @Schema(title = "유저 북마크 여부",description = "0:북마크 전 1:북마크 후")
    private int isBook;

    @Schema(title = "가게 사진")
    private List<String> pics;
    @Schema(title = "상세 메뉴")
    private List<DetailMenu> menus;
    @Schema(title = "정육점 리뷰")
    private List<ReviewDetail> reviews;
}

