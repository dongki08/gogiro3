package com.green.gogiro.admin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminSigninDto {
    @Schema(defaultValue = "admin1")
    @NotBlank(message="아이디를 입력해주세요")
    private String email;
    @Schema(defaultValue = "1212")
    @NotBlank(message="비밀번호를 입력해주세요")
    private String upw;
}
