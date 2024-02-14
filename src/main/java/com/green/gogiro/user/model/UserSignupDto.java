package com.green.gogiro.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.gogiro.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import static com.green.gogiro.common.Const.*;

@Data
@Schema(title = "회원가입Dto")
public class UserSignupDto {
    @JsonIgnore
    private int iuser;
    @Schema(title = "이메일")
    @NotBlank(message = "아이디를 입력해주세요")
    @Pattern(regexp = REGEXP_USER_ID, message = "이메일 형식이 틀렸습니다")
    private String email;
    @Schema(title = "비밀번호")
    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String upw;
    @Schema(title = "비밀번호 확인")
    @NotBlank(message = "확인할 비밀번호를 입력해 주세요")
    private String checkUpw;
    @Schema(title = "이름")
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @Schema(title = "닉네임")
    @NotBlank(message = "닉네임을 입력해 주세요")
    @Size(min=1, max= 10, message = "닉네임은 한 글자 이상 10자 이하 이여야 합니다")
    private String nickname;
    @Schema(title = "생년월일")
    @NotBlank(message = "생년월일을 입력해 주세요")
    @Pattern(regexp = REGEXP_USER_BIRTH, message = "생년월일 형식이 틀렸습니다")
    private String birth;
    @Schema(title = "성별",description = "'남','여' 로  해야함 '남자','여자' X",defaultValue = "'남','여' 로  해야함 '남자','여자' X")
    @NotBlank(message = "성별을 선택해 주세요")
    @Pattern(regexp = REGEXP_USER_GENDER, message = "성별을 선택해 주세요")
    private String gender;
    @Schema(title = "주소")
    @NotBlank(message = "주소를 입력해 주세요")
    private String address;
    @Schema(title = "전화번호",defaultValue = "01000000000")
    @NotBlank(message = "전화번호를 입력해 주세요")
    @Pattern(regexp = REGEXP_USER_TEL, message = "전화번호 형식이 틀렸습니다")
    private String tel;
    @JsonIgnore
    private String pic;
    @JsonIgnore
    private MultipartFile file;
}
