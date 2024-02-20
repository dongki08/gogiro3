package com.green.gogiro.admin.model;

import lombok.Data;

@Data
public class ReportedVo {
    private int pk;//해당 글 pk
    private String contents;//신고물 내용?제목?
    private String writerNm;//게시물 작성자 실명?닉네임?
    private int count;//신고 수
    private int state;//상태
}
