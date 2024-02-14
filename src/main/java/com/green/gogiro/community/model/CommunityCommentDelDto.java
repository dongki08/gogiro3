package com.green.gogiro.community.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CommunityCommentDelDto {
    @JsonIgnore
    private int iuser;
    @Schema(title = "댓글pk")
    private int icomment;
}
