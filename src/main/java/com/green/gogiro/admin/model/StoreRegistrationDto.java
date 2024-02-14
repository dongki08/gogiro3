package com.green.gogiro.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class StoreRegistrationDto {
    @JsonIgnore
    private int ishop;
    @NotBlank
    private int imeat;
    @NotBlank
    private List<Integer> ifacil;
    @NotBlank
    @Size(min = 1, max = 30, message = "30자 이상 작성할 수 없습니다.")
    private String name;
    @NotBlank
    @Size(min = 1, max = 100, message = "100자 이상 작성할 수 없습니다.")
    private String location;
    private String open;
    private String tel;
    private String x;
    private String y;
    private List<MultipartFile> pics = new ArrayList<>();
}
