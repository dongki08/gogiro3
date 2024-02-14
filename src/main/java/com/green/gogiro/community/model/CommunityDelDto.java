package com.green.gogiro.community.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "커뮤니티 삭제Dto")
public class CommunityDelDto {
    @JsonIgnore
    private int iuser;
    @Schema(title = "커뮤니티pk")
    private int iboard;
}
