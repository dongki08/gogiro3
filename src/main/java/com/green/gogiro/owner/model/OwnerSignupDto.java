package com.green.gogiro.owner.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Valid
public class OwnerSignupDto {
private String id;
private String upw;
private String checkPw;
@Size(min = 10,max = 10)
private String num;
private String name;
private String shopName;
private String x;
private String y;
private String location;
private Long imeat;

}
