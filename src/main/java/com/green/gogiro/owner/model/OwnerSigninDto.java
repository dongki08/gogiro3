package com.green.gogiro.owner.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class OwnerSigninDto {
    private String email;
    private String upw;
}
