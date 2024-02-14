package com.green.gogiro.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookmarkShopVo {
    @Schema(title = "고기 종류",description = "정육점은 0으로 나옴")
    private int imeat;//0: 정육점
    @Schema(title = "식당pk")
    private int ishop;
    @Schema(title = "상호명")
    private String name;
    @Schema(title = "가게위치")
    private String location;
    @Schema(title = "영업시간")
    private String open;
    @Schema(title = "전화번호")
    private String tel;
    @Schema(title = "고기 종류")
    private String mtype;
    @Schema(title = "가게 사진")
    private String pic;
    @Schema(title = "북마크한 가게 총 갯수")
    private int count;
    @Schema(title = "편의시설 정보")
    private List<String> facilities = new ArrayList<>();
    @JsonIgnore
    private String createdAt;
}
