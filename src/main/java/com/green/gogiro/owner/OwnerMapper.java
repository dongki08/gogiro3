package com.green.gogiro.owner;


import com.green.gogiro.owner.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OwnerMapper {
    int insStoreRegistrationPics(StoreRegistrationPicsVo dto);
    int insStoreRegistration(StoreRegistrationDto dto);

    int insShopMenu(ShopMenuDto dto);

    int updShopMenu(ShopMenuUpdDto dto);

    String selPicNm(int imenu);

    int insButcherShop(ButcherInsDto dto);

    //수정
    int insButcherPics(ButcherInsDto dto);

    //수정
    int insButcherPics(ButcherPicsUpdDto dto);

    int insButcherMenu(ButcherMenuInsDto dto);

    String selButcherMenuPicNm(int ibutMenu);

    int updButcherMenu(ButcherMenuUpdDto dto);

    int insShopFacility(StoreRegistrationDto dto);

    List<ShopSelPicsNumDto> selShopPics(List<Integer> ishopPics);

    int delShopPics(List<Integer> ishopPics);

    int insShopPic(ShopUpdDto dto);

    int delButcherPics(List<Integer> ibutPics);

    List<ButcherPicsProcVo> selButcherPics(List<Integer> ibutPics);





}