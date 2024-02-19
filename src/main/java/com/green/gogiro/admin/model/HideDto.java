package com.green.gogiro.admin.model;

import lombok.Data;

@Data
public class HideDto {
    private int check;//글 종류(0:고기잡담 글, 1:고기잡담 댓글, 2:고기집 후기, 3:정육점 후기)
    private int pk;//해당 글 pk
}
