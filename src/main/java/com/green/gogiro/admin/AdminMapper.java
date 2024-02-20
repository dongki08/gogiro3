package com.green.gogiro.admin;

import com.green.gogiro.admin.model.ConfirmDto;
import com.green.gogiro.admin.model.ShopVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<ShopVo> shopList();
    int confirmShop(ConfirmDto dto);
}
