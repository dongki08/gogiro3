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
    private Long ishop;
    private Long imeat;
    private List<String> pics;
    private String name;
    private String location;
    private String open;
    private String tel;
    private String x;
    private String y;
    private int deposit;
    private List<FacilitiesVo> facilities = new ArrayList<>();

}
