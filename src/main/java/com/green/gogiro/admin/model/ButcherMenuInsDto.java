package com.green.gogiro.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class ButcherMenuInsDto {
    private int ibutcher;
    private String menu;
    private int price;
    @JsonIgnore
    private String fileNm;
    @JsonIgnore
    private int ibutMenu;
    @JsonIgnore
    private MultipartFile pic;
}
