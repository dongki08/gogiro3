package com.green.gogiro.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReviewPicsInsVo {
    @JsonIgnore
    private int checkShop;
    private int ireview;
    private List<String> pics = new ArrayList<>();
}
