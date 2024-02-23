package com.green.gogiro.admin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdminSigninDto {
    @Schema(defaultValue = "dd44@naver.com")
    private String email;
    @Schema(defaultValue = "1212")
    private String upw;
}
