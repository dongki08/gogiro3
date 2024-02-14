package com.green.gogiro.community.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "커뮤니티 리스트")
public class CommunitySelDto {
    @Schema(title = "페이지", defaultValue = "1")
    private int page;

    @Schema(title = "검색", defaultValue = " ")
    private String search;
    @Schema(title="필터링 구분", defaultValue = "0")
    private int filter;//0: 작성 순, 1: 추천 순
    @JsonIgnore
    private int startIdx;

    @JsonIgnore
    private int rowCount = 10;

    public void setPage(int page) {
        this.startIdx = (page - 1) * rowCount;
    }

}
