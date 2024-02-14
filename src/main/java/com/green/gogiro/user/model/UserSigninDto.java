package com.green.gogiro.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static com.green.gogiro.common.Const.REGEXP_USER_ID;

@Data
@Schema(title = "로그인Dto")
public class UserSigninDto {
    @Schema(title = "이메일",defaultValue = "dd11@naver.com")
    @NotBlank(message = "아이디를 입력해주세요")
    @Pattern(regexp = REGEXP_USER_ID, message = "이메일 형식이 틀렸습니다")
    private String email;
    @Schema(title = "비밀번호",defaultValue = "1212")
    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String upw;
}
