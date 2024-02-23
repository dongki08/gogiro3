package com.green.gogiro.owner.model;

import com.green.gogiro.entity.shop.ShopEntity;
import com.green.gogiro.shop.model.ShopFacilityVo;

import java.util.List;

public interface OwnerMainQdslRepository {
  OwnerManagementVo selDetailShop(long ishop, int checkShop);

  List<FacilitiesVo> selFacilityByShop(long ishop);

}
