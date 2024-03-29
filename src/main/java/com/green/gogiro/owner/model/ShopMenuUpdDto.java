package com.green.gogiro.owner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ShopMenuUpdDto {

    private int imenu;
    @JsonIgnore
    private int ishop;
    @JsonIgnore
    private MultipartFile pic;
    @JsonIgnore
    private String fileNm;

}
