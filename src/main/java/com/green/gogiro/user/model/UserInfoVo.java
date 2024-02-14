package com.green.gogiro.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "유저정보Vo")
public class UserInfoVo {
    @Schema(title = "유저pk")
    private int iuser;
    @Schema(title = "이메일")
    private String email;
    @Schema(title = "이름")
    private String name;
    @Schema(title = "닉네임")
    private String nickname;
    @Schema(title = "생년월일")
    private String birth;
    @Schema(title = "성별")
    private String gender;
    @Schema(title = "주소")
    private String address;
    @Schema(title = "프로필사진")
    private String pic;
    @Schema(title = "전화번호",defaultValue = " ")
    private String tel;
}
