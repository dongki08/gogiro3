package com.green.gogiro.owner.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Valid
public class OwnerSignupDto {
    @Schema(title = "아이디")
    @NotBlank(message = "아이디를 입력해 주세요")
    private String id;
    @Schema(title = "비밀번호")
    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String upw;
    @Schema(title = "비밀번호 확인")
    @NotBlank(message = "확인할 비밀번호를 입력해 주세요")
    private String checkPw;
    @Size(min = 10, max = 10)
    private String num;
    @Schema(title = "이름")
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @Schema(title = "상호명")
    @NotBlank(message = "상호명을 입력해 주세요")
    private String shopName;
    private String x;
    private String y;
    @Schema(title = "주소")
    @NotBlank(message = "주소를 입력해 주세요")
    private String location;
    @Schema(title = "고기종류",description = "정육점은 0,고깃집은 1,2,3,4,5")
    @Min(value = 0)
    @Max(value = 5)
    private Long imeat;

}
