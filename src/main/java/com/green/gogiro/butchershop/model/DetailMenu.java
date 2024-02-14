package com.green.gogiro.butchershop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailMenu {
    @JsonIgnore
    private int ibutcher;
    private int ibutMenu;
    private int price;
    private String menu;
    private String pic;
}
