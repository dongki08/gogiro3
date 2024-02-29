package com.green.gogiro.owner.model;

import com.green.gogiro.shop.model.ShopFacilityVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerManagementVo {
    private int checkShop;
    private Long ishop;
    private Long imeat;
    private List<OwnerShopPicsProcVo> pics;
    private String name;
    private String location;
    private String open;
    private String tel;
    private String x;
    private String y;
    private int deposit;
    private String number;
    private List<Long> facilities = new ArrayList<>();

}
