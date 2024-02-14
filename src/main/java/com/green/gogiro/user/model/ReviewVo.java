package com.green.gogiro.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ReviewVo {
    @Schema(title = "가게 구분(0:식당,1:정육점)")
    private int checkShop;//가게구분(0:고기집, 1: 정육점)
    @Schema(title = "리뷰pk")
    private int ireview;//후기pk
    @Schema(title = "식당pk")
    private int ishop;//가게pk
    @Schema(title = "상호명")
    private String name;
    @Schema(title = "별점")
    private int star;//별점
    @Schema(title = "후기")
    private String review;//후기
    @Schema(title = "가게 사진")
    private String pic;
    @Schema(title = "리뷰 총 갯수")
    private int count;
    @Schema(title = "작성 날짜")
    private String createdAt;
    @Schema(title = "북마크 여부")
    private String isBook;
    @Schema(title = "리뷰 사진")
    private List<String> pics;//(후기 사진)
}
