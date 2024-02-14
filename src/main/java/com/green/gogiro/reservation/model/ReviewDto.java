package com.green.gogiro.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReviewDto {
    @JsonIgnore
    private int iuser;
    @JsonIgnore
    private int ireview;

    @Schema(title = "고기집 0,정육점 1")
    @Min(value = 0)
    @Max(value = 1)
    private int checkShop;
    @Schema(title = "예약pk")
    @Min(value = 1)
    private int ireser;
    @Schema(title = "")
    private int ishop;
    @Schema(title = "별점")
    @Min(value = 1)
    @Max(value = 5,message = "별점은 최대 5점 입니다")
    private int star;
    @Schema(title = "리뷰")
    @NotBlank
    @Size(min = 1, max = 50, message = "50자 초과 작성 할 수 없습니다.")
    private String review;
    @Schema(title = "리뷰 사진",description = "최대 5장")
    @JsonIgnore
    private List<String> pics = new ArrayList<>();

    @JsonIgnore
    private List<MultipartFile> files = new ArrayList<>();
}
