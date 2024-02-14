package com.green.gogiro.butchershop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.gogiro.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ButcherSelDto {
    @Schema(title = "페이지", defaultValue = "1")
    private int page;
    @Schema(title = "검색",defaultValue = " ")
    private String search;

    @Schema(title = "필터링 구분", description = "0")
    private int filter; // 0: 등록순, 1: 별점순, 2: 북마크순

    @JsonIgnore
    private int startIdx;

    @JsonIgnore
    private final int rowCount = 10;

    public void setPage(int page){
        this.page = page;
        this.startIdx = (this.page -1) * rowCount;
    }
}
