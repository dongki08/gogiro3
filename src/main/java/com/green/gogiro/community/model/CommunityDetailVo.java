package com.green.gogiro.community.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommunityDetailVo {
    @Schema(title = "커뮤니티pk")
    private int iboard;
    @Schema(title = "유저pk")
    private int iuser;
    @Schema(title = "작성자 이름")
    private String name;
    @Schema(title = "작성자 프로필 사진")
    private String writerPic;
    @Schema(title = "제목")
    private String title;
    @Schema(title = "내용")
    private String contents;
    @Schema(title = "작성일")
    private String createdAt;
    @Schema(title = "사진")
    private List<CommunityBySelPicsDto> pics = new ArrayList<>();
    @Schema(title = "이전글")
    private CommunitySelBeAfDto be;
    @Schema(title = "다음글")
    private CommunitySelBeAfDto af;
    @Schema
    private List<CommunityCommentVo> comments = new ArrayList<>();
}
