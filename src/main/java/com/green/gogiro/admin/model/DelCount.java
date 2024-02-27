package com.green.gogiro.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DelCount {
    private int check;
    //글 종류(0:고기잡담 글, 1:고기잡담 댓글, 2:고기집 후기, 3:정육점 후기)
    private int pk;//해당 글 pk

    private String table;

    private String column;
    public void setCheck(int check){
        this.check= check;
        switch(check){
            case 0:
                this.table="community_count";
                this.column="board";
                break;
            case 1:
                this.table="comment_count";
                this.column="comment";
                break;
            case 2:
                this.table="shop_review_count";
                this.column="review";
                break;
            case 3:
                this.table="but_review_count";
                this.column="review";
                break;
        }
    }
}
