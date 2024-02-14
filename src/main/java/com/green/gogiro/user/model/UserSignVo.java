package com.green.gogiro.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSignVo {
    private final int result;
    private int iuser;
    private String name;
    private String nickname;
    private String birth;
    private String gender;
    private String address;
    private String tel;
    private String pic;
    private String accessToken;
}
