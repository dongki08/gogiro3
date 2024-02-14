package com.green.gogiro.community.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommunityUpdDto {
    @JsonIgnore
    private int iuser;
    @Schema(title = "커뮤니티pk")
    @Min(value = 1)
    private int iboard;
    @Schema(title = "삭제할 사진pk",example = "[]")
    private List<Integer> icommuPics;
    @Schema(title = "제목")
    @NotBlank
    @Size(min = 1, max = 30, message = "30자 초과 작성 할 수 없습니다.")
    private String title;
    @Schema(title = "내용")
    @NotBlank
    @Size(min = 1, max = 300, message = "300자 초과 작성 할 수 없습니다.")
    private String contents;

    @JsonIgnore
    @Schema(title = "사진")
    private List<String> pics = new ArrayList<>();

    private List<MultipartFile> files;
}
