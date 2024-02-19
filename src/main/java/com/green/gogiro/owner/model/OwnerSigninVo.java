package com.green.gogiro.owner.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerSigninVo {
    private Long ishop;
    private Long iuser;
    private String accessToken;
}
