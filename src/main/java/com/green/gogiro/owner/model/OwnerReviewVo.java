package com.green.gogiro.owner.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerReviewVo {
    @Schema(title = "가게구분(0:고기,1:고기)")
    private int checkShop;
    @Schema(title = "리뷰pk")
    private Long ireview;
    @Schema(title = "가게pk")
    private Long ishop;
    @Schema(title = "유저pk")
    private Long iuser;
    @Schema(title = "별점")
    private int star;
    @Schema(title = "사장님 코멘트")
    private String comment;
    @Schema(title = "코멘트 여부")
    private Integer exist;
    @Schema(title = "리뷰")
    private String review;
    @Schema(title = "리뷰 작성일")
    private String createdAt;
    @Schema(title = "코멘트 작성일")
    private String updatedAt;
    @Schema(title = "리뷰사진")
    private List<String> pics = new ArrayList<>();

}
