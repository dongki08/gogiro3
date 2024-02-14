package com.green.gogiro.butchershop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ReviewPicVo {
    @JsonIgnore
    private int ireview;
    private String pic;
}
