package com.green.gogiro.community.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CommunityPicsVo {
    @Schema(title = "커뮤니티pk")
    private int iboard;
    @Schema(title = "사진")
    private String pic;
}
