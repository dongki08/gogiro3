package com.green.gogiro.admin.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StoreRegistrationPicsVo {
    private int ishop;
    private List<String> pics = new ArrayList<>();
}
