package com.green.gogiro.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CancelReportDto {
    @Min(value=0,message="글 종류 값이 유효하지 않습니다")
    @Max(value=3,message="글 종류 값이 유효하지 않습니다")
    private int check;
    //글 종류(0:고기잡담 글, 1:고기잡담 댓글, 2:고기집 후기, 3:정육점 후기)
    @Min(value=1,message="pk는 최소 1 이상이어야 합니다")
    private int pk;//해당 글 pk
    @JsonIgnore
    private String table;
    @JsonIgnore
    private String column;
    public void setCheck(int check){
        this.check= check;
        switch(check){
            case 0:
                this.table="community";
                this.column="board";

                break;
            case 1:
                this.table="community_comment";
                this.column="comment";

                break;
            case 2:
                this.table="shop_review";
                this.column="review";
                break;
            case 3:
                this.table="but_review";
                this.column="review";
                break;
        }
    }
}
