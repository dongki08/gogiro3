package com.green.gogiro.admin;

import com.green.gogiro.admin.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    AdminSigninProcVo checkAdmin(String email);
    List<ShopVo> shopList(String search);
    int confirmShop(ConfirmDto dto);
    List<ReportedVo> reportList(ReportDto dto);
    int hide(HideDto dto);
    int cancelReport(CancelReportDto dto);
    int delCount(DelCount count);
    List<Integer> beforeBlackList();
    List<BlackVo> blackList(List<Integer> list);
    int suspendAccount(CheckDto dto);
}
