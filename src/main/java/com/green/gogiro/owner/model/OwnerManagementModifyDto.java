package com.green.gogiro.owner.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OwnerManagementModifyDto {
    private long imeat;
    private String name;
    private String location;
    @Schema(title = "삭제할 사진pk",example = "[]")
    private List<Integer> ishopPics = new ArrayList<>();
    private String open;
    private String tel;
    private String x;
    private String y;
    private Integer deposit;
    @Schema(title = "삭제할 편의시설pk",example = "[]")
    private List<Integer> facilities = new ArrayList<>();
}
