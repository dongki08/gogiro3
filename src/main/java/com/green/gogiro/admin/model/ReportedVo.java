package com.green.gogiro.admin.model;

import lombok.Data;

@Data
public class ReportedVo {
    //상태는 어떤 상태를 나타내는 건가요?
    private int check;
    //글 종류(0:고기잡담 글, 1:고기잡담 댓글, 2:고기집 후기, 3:정육점 후기)
    private int pk;//해당 글 pk
    private String contents;//신고물 내용?제목?
    private String writerNm;//게시물 작성자 실명?닉네임?
    private int count;//신고 수
    private int state;//상태
}
