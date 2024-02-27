package com.green.gogiro.owner.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OwnerManagementModifyVo {
    private int checkShop;
    private Long ishop;
    private List<String> pics = new ArrayList<>();
}
