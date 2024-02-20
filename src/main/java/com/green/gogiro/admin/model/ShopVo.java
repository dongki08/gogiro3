package com.green.gogiro.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ShopVo {
    /*1.상세 주소를 위도,경도를 이용해서 부르고 싶으시면 x,y로 보내드리겠습니다
    2.그냥 가게 주인이 직접 쓴 주소(문자열)로 받고 싶으시면 location으로
    보내드리겠습니다
    3.고기 종류도 나타내고 싶으시면 checkShop에
    0:정육점, 1:돼지, 2:소, 3:닭, 4:오리, 5:양으로 보내드릴 수 있습니다*/
    private int checkShop;//가게 구분(0:고기집, 1:정육점)
    private int ishop;//가게 pk(최소 1 이상)
    private String name;//가게 이름
    private String x;//경도
    private String y;//위도
    private String tel;//전화번호
    private int confirm;//승인 여부(0:대기, 1:확정, 2: 거절, 3:퇴출)
    private String pic;
    @JsonIgnore
    private String createdAt;
    private boolean isShop;

    //가게 사진(여러 장으로 받고 싶으시면 리스트로 변경하겠습니다)

    public void setCheckShop(int checkShop){
        this.checkShop= checkShop;
        this.isShop=(checkShop==0);
    }

}
