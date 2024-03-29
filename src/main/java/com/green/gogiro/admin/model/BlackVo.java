package com.green.gogiro.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BlackVo {
    private int iuser;
    /*1.이거 페이지에 나타나는 사용자 순서는 무슨 순서대로
    보내드리는 게 좋을까요?
    2.이름 부분을 실명으로 하고 싶으신지 닉네임으로 하고 싶으신지
    궁금합니다*/
    private String name;//이름
    private String id;//USER: EMAIL 형식, OWNER: 일반 형식
    private String number;//사업자 등록 번호(가게 주인만)
    private int state;//상태(잠금여부 0:정상 1:잠금)
}
