package com.green.gogiro.user.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.gogiro.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.parameters.P;
import org.springframework.web.multipart.MultipartFile;

@Data
@Schema(title = "유저정보 수정 Dto")
public class UserUpdDto {
    @JsonIgnore
    private int iuser;

    @Schema(title = "닉네임")
    @NotBlank
    @Size( min = 1,max= 10,message = "닉네임은 한 글자 이상 10자 이하 이여야 합니다")
    private String nickname;
    @Schema(title = "주소")
    @NotBlank
    private String address;
    @JsonIgnore
    private String pic;
    @Schema(title = "전화번호")
    @Pattern(regexp = Const.REGEXP_USER_TEL , message = "전화번호 형식을 맞춰 주세요")
    private String tel;
    @JsonIgnore
    private MultipartFile file;
}
