package com.green.gogiro.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopReviewDetail {
    @JsonIgnore
    private int ishop;
    @Schema(title = "유저pk")
    private int iuser;
    @Schema(title = "작성자 사진")
    private String writerPic;
    @Schema(title = "리뷰 pk")
    private int ireview;
    @Schema(title = "닉네임")
    private String nickname;
    @Schema(title = "별점")
    private int star;
    @Schema(title = "리뷰")
    private String review;
    @Schema(title = "리뷰 사진")
    private List<String> pic = new ArrayList<>();
}
