package com.green.gogiro.butchershop.model;

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
public class ReviewDetail {
    @JsonIgnore
    private int ibutcher;
    @Schema(title = "유저pk")
    private int iuser;
    @Schema(title = "작성자 사진")
    private String writerPic;
    @Schema(title = "리뷰pk")
    private int ireview;
    @Schema(title = "닉네임")
    private String nickname;
    @Schema(title = "별점")
    private int star;
    @Schema(title = "리뷰 내용")
    private String review;
    @Schema(title = "사진")
    private List<String> pics = new ArrayList<>();
}
