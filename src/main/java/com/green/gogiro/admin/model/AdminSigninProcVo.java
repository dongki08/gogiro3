package com.green.gogiro.admin.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminSigninProcVo {
    private Long iuser;
    private String upw;
    private String role;
}
