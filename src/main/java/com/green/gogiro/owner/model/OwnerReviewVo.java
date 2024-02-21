package com.green.gogiro.owner.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerReviewVo {
    private Long ishop;
    private Long iuser;
    private String comment;
    private String review;
    private String createdAt;
    private List<String> pics = new ArrayList<>();

}
