package com.green.gogiro.shop;

import com.green.gogiro.common.Const;
import com.green.gogiro.common.MyFileUtils;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.security.AuthenticationFacade;
import com.green.gogiro.shop.model.*;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.green.gogiro.exception.AuthErrorCode.*;


@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopMapper mapper;

    private final AuthenticationFacade authenticationFacade;
    private final MyFileUtils myFileUtils;

    public List<ShopSelVo> getShopList(ShopSelDto dto) {
        if(Pattern.matches(Const.REGEXP_PATTERN_SPACE_CHAR_TYPE_2,dto.getSearch())){
            throw new RestApiException(NOT_CONTENT);
        }
        Integer category = mapper.selShopCategory(dto.getCategory());
        if(dto.getCategory() == 0){
            category = Const.SUCCESS;
        }
        if (category == null) {
            throw new RestApiException(INVALID_CATEGORY);
        }
        List<ShopSelVo> shopList = mapper.selShopAll(dto);
        List<Integer> shopPk = new ArrayList<>();
        Map<Integer, ShopSelVo> shopMap = new HashMap<>();
        for (ShopSelVo vo : shopList) {
            shopPk.add(vo.getIshop());
            shopMap.put(vo.getIshop(), vo);
            vo.setCount(mapper.selShopListCount(dto.getCategory()));
        }

        List<ShopPicsVo> picList = mapper.selShopPicList(shopPk);
        for(ShopPicsVo vo: picList){
            shopMap.get(vo.getIshop()).getPics().add(vo.getPic());
        }
        List<ShopFacilityVo> shopFacilities = mapper.selShopFacility(shopPk);
        for(ShopFacilityVo vo : shopFacilities){
            shopMap.get(vo.getIshop()).getFacilities().add(vo.getFacility());
        }

        return shopList;
    }

    public ShopDetailVo getShopDetail(int ishop) {
        ShopEntity entity = mapper.selShopEntity(ishop);
        if (entity == null) {
            throw new RestApiException(VALID_SHOP);
        }
        int i;
        try {
            i= authenticationFacade.getLoginUserPk();
        } catch(Exception e) {
            i= 0;
        }
        ShopDto dto = new ShopDto(i,ishop);
        ShopDetailVo shopDetailList = mapper.selShopDetail(dto);
        List<String> facilityList = mapper.shopFacilities(ishop);
        List<ShopDetailMenu> menuList = mapper.selMenuDetail(ishop);
        List<String> picList = mapper.selShopPics(ishop);
        shopDetailList.setFacilities(facilityList);
        shopDetailList.setMenus(menuList);
        shopDetailList.setPics(picList);


        List<Integer> ireviewList = new ArrayList<>();
        Map<Integer, ShopReviewDetail> reviewDetailMap = new HashMap<>();


        List<ShopReviewDetail> reviewList = mapper.selReviewDetail(ishop);
        for (ShopReviewDetail review : reviewList) {

            ireviewList.add(review.getIreview());
            reviewDetailMap.put(review.getIreview(), review);
        }
        List<ShopReviewPicVo> reviewPicList = mapper.selReviewPicDetail(ishop);
        for (ShopReviewPicVo pics : reviewPicList) {
            reviewDetailMap.get(pics.getIreview()).getPic().add(pics.getPic());
        }
        shopDetailList.setReviews(reviewList);
        return shopDetailList;
    }


    public ResVo toggleShopBookmark(ShopBookmarkDto dto) {
        ShopEntity entity = mapper.selShopEntity(dto.getIshop());
        dto.setIuser(authenticationFacade.getLoginUserPk());
        if(entity == null) {
            throw new RestApiException(VALID_SHOP);
        }
        dto.setOn(mapper.selShopBookmark(dto) == null);
        if(dto.isOn()) {
            mapper.shopBookmarkOn(dto);
            return new ResVo(Const.ON);
        } else {
            mapper.shopBookmarkOff(dto);
            return new ResVo(Const.OFF);
        }
    }

    public ShopMainVo selMainPage() {
        List<ShopMainGogiVo> gogiVoList = mapper.selMainShop();
        List<ShopMainCommuVo> commuVoList = mapper.selMainCommunity();
        ShopMainVo vo = new ShopMainVo();
        vo.setGogi(gogiVoList);
        vo.setCommu(commuVoList);
        return vo;
    }
}
