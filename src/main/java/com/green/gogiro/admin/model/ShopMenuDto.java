package com.green.gogiro.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ShopMenuDto {

    private int imenu;

    private int ishop;
    @JsonIgnore
    private MultipartFile pic;
    @JsonIgnore
    private String fileNm;
}
