package com.green.gogiro.owner.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OwnerManagementVo {
    private Long ishop;
    private Long imeat;
    private String name;
    private String location;
    private String open;
    private String tel;
    private String x;
    private String y;
    private int deposit;
    List<String> pics = new ArrayList<>();

}
