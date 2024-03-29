package com.green.gogiro.shop;


import com.green.gogiro.owner.model.OwnerShopPicsProcVo;
import com.green.gogiro.shop.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMapper {

    List<ShopSelVo> selShopAll(ShopSelDto dto);
    List<ShopPicsVo> selShopPicList(List<Integer> pk);
    List<ShopFacilityVo> selShopFacility(List<Integer> ishop);

    int selReviewCount(int ishop);

    int selShopListCount(int category);

    ShopDetailVo selShopDetail(ShopDto dto);
    List<String> shopFacilities(int ishop);
    List<ShopDetailMenu> selMenuDetail(int ishop);
    List<String> selShopPics(int ishop);
    List<ShopReviewDetail> selReviewDetail(int ishop);
    List<ShopReviewPicVo> selReviewPicDetail(int ishop);



    Integer selShopBookmark(ShopBookmarkDto dto);
    int shopBookmarkOn(ShopBookmarkDto dto);
    int shopBookmarkOff(ShopBookmarkDto dto);

    ShopModel selShopEntity(int ishop);
    Integer selShopCategory(int imeat);

    int selIshopForTest();

    List<ShopMainGogiVo> selMainShop();
    List<ShopMainCommuVo> selMainCommunity();

    List<OwnerShopPicsProcVo> selByShopPics(int ishop);

    int insFacilities(long ishop,List<Integer> ifacil);

    int delFacilities(long ishop);

}
