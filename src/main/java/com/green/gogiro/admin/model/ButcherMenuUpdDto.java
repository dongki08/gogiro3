package com.green.gogiro.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class ButcherMenuUpdDto {
    private int ibutMenu;
    private int ibutcher;
    @JsonIgnore
    private String fileNm;
    @JsonIgnore
    private MultipartFile file;

}
