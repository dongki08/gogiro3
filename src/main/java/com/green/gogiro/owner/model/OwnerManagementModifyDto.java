package com.green.gogiro.owner.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.green.gogiro.common.Const.REGEXP_PATTERN_SPACE_CHAR;
import static com.green.gogiro.common.Const.REGEXP_PATTERN_SPACE_CHAR_3;

@Data
public class OwnerManagementModifyDto {
    private long imeat;
    @Schema(defaultValue = " ")

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
