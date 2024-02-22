package com.green.gogiro.owner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewCommentDto {
    @JsonIgnore
    private int iuser;

    private int ireview;

    @Schema(title = "고기집 0,정육점 1")
    @Min(value = 0,message="가게구분 값이 잘못되었습니다")
    @Max(value = 1,message="가게구분 값이 잘못되었습니다")
    private int checkShop;

    @JsonIgnore
    @Schema(title = "가게pk")
    @Min(value = 1,message = "고기집 혹은 정육점 pk를 보내주세요")
    private int ishop;

    @Schema(title = "코멘트")
    @NotBlank
    @Size(min = 1, max = 50, message = "코멘트 내용은 1~50글자 사이를 만족해야 합니다")
    private String comment;
}
