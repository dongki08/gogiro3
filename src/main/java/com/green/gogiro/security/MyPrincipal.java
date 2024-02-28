package com.green.gogiro.security;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPrincipal {
    private Long iuser;
    private Long ishop;
    private int checkShop;
    private String role;


}
