package com.green.gogiro.owner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class SelButcherPickupMenuProcVo {
    private int menuCount;
    private int ibutMenu;
    @JsonIgnore
    private int ireser;
    private String menu;
}
