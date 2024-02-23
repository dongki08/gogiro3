package com.green.gogiro.owner.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OwnerManagementModifyDto {
    private Long imeat;
    private String name;
    private String location;
    private String open;
    private String tel;
    private String x;
    private String y;
    private int deposit;
    private List<Integer> facility = new ArrayList<>();
}
