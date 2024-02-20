package com.green.gogiro.owner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ButcherPicVo {
    private int ibutcher;
    private List<String> pics = new ArrayList<>();
}
