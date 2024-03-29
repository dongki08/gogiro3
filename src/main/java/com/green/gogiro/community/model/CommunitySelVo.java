package com.green.gogiro.community.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommunitySelVo {
    @Schema(title = "커뮤니티pk")
    private int iboard;
    @Schema(title = "게시글번호")
    private int boardNum;
    @Schema(title = "유저pk")
    private int iuser;
    @Schema(title = "작성자 이름")
    private String writerName;
    @Schema(title = "작성자 프로필 사진")
    private String writerPic;
    @Schema(title = "제목")
    private String title;
    @Schema(title = "내용")
    private String contents;
    @Schema(title = "신고 횟수")
    private int count;
    @Schema(title = "작성일")
    private String createdAt;
    @Schema(title = "사진")
    private List<String> pics = new ArrayList<>();

    @Schema(title = "게시글 총 갯수")
    private int boardAllCount;

    @Schema(title = "총 좋아요 수")
    private int totalFav;

    @Schema(title = "공지")
    private int announce;
}
