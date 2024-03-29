package com.green.gogiro.owner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class ButcherInsDto {
    private String name;
    private String location;
    private String open;
    private String tel;
    private String x;
    private String y;
    @JsonIgnore
    private int ibutcher;
    @JsonIgnore
    private List<String> pics = new ArrayList<>();
    @JsonIgnore
    private List<MultipartFile> files;
}
