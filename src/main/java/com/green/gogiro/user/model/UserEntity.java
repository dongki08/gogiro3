package com.green.gogiro.user.model;

import lombok.Data;

@Data
public class UserEntity {
    private int iuser;
    private String email;
    private String upw;
    private String name;
    private String nickname;
    private String birth;
    private String gender;
    private String address;
    private String pic;
    private String tel;
    private String createdAt;
}
