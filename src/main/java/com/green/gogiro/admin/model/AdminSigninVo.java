package com.green.gogiro.admin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminSigninVo {
    private int result;
    private Long iuser;
    private String accessToken;
}
