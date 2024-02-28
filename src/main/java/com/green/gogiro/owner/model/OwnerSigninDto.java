package com.green.gogiro.owner.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class OwnerSigninDto {
    @Schema(defaultValue = "test2")
    private String email;
    @Schema(defaultValue = "1212")
    private String upw;
}
