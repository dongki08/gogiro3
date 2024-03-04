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
    @Pattern(regexp = REGEXP_PATTERN_SPACE_CHAR_3,message = "띄어쓰기 공백은 불가능 합니다")
    private String name;
    @Pattern(regexp = REGEXP_PATTERN_SPACE_CHAR_3,message = "띄어쓰기 공백은 불가능 합니다")
    private String location;
    @Schema(title = "삭제할 사진pk",example = "[]")
    private List<Integer> ishopPics = new ArrayList<>();
    @Pattern(regexp = REGEXP_PATTERN_SPACE_CHAR_3,message = "띄어쓰기 공백은 불가능 합니다")
    private String open;
    @Pattern(regexp = REGEXP_PATTERN_SPACE_CHAR_3,message = "띄어쓰기 공백은 불가능 합니다")
    private String tel;
    @Pattern(regexp = REGEXP_PATTERN_SPACE_CHAR_3,message = "띄어쓰기 공백은 불가능 합니다")
    private String x;
    @Pattern(regexp = REGEXP_PATTERN_SPACE_CHAR_3,message = "띄어쓰기 공백은 불가능 합니다")
    private String y;
    private Integer deposit;
    @Schema(title = "삭제할 편의시설pk",example = "[]")
    private List<Integer> facilities = new ArrayList<>();
}
