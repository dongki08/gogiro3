package com.green.gogiro.community.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CommunityCommentVo {
    @JsonIgnore
    private int iboard;

    private int icomment;
    private int writerPk;
    private String writerName;
    private String comment;
    private String createdAt;
}
