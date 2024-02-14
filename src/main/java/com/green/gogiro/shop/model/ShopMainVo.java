package com.green.gogiro.shop.model;

import lombok.Data;

import java.util.List;

@Data
public class ShopMainVo {
    private List<ShopMainGogiVo> gogi;
    private List<ShopMainCommuVo> commu;
}
