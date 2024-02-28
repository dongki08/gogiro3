package com.green.gogiro.admin;

import com.green.gogiro.admin.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<ShopVo> shopList(ShopDto dto);
    int confirmShop(ConfirmDto dto);
    List<ReportedVo> reportList(ReportDto dto);
    int delCount(DelCount count);
    List<Integer> beforeBlackList();
    List<BlackVo> blackList(BlackDto dto);
    int suspendAccount(CheckDto dto);
}
