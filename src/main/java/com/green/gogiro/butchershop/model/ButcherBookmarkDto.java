package com.green.gogiro.butchershop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ButcherBookmarkDto {
    @JsonIgnore
    private int iuser;
    @Valid
    @Min(value = 1,message = "잘못된 pk가 입력 되었습니다.")
    private int ibutcher;
    @JsonIgnore
    private boolean on;
}
