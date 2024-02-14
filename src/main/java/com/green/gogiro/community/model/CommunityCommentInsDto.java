package com.green.gogiro.community.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommunityCommentInsDto {
    @Schema(title = "커뮤니티pk")
    private int iboard;

    @JsonIgnore
    private int iuser;
    @Schema(title = "댓글내용")
    @NotBlank
    @Size(min = 1, max = 50, message = "50자 초과 작성 할 수 없습니다.")
    private String contents;
}
