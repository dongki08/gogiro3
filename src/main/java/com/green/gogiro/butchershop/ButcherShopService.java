package com.green.gogiro.butchershop;

import com.green.gogiro.butchershop.model.*;
import com.green.gogiro.common.Const;
import com.green.gogiro.common.MyFileUtils;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.security.AuthenticationFacade;
import com.green.gogiro.user.UserMapper;
import com.green.gogiro.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.green.gogiro.exception.AuthErrorCode.NOT_CONTENT;
import static com.green.gogiro.exception.AuthErrorCode.VALID_SHOP;

@Service
@RequiredArgsConstructor
public class ButcherShopService {
    private final ButcherShopMapper mapper;
    private final UserMapper userMapper;

    private final AuthenticationFacade authenticationFacade;
    private final MyFileUtils myFileUtils;

    public List<ButcherSelVo> getButList(ButcherSelDto dto) {
        if(Pattern.matches(Const.REGEXP_PATTERN_SPACE_CHAR_TYPE_2,dto.getSearch())){
            throw new RestApiException(NOT_CONTENT);
        }
        if(dto.getPage() <= 0){
            throw new RestApiException(AuthErrorCode.INVALID_PAGE);
        }
        int count = mapper.selListCount();
        List<ButcherSelVo> list = mapper.selButcherShopAll(dto);
        List<Integer> pk = new ArrayList<>();
        Map<Integer, ButcherSelVo> butMap = new HashMap<>();
        for (ButcherSelVo vo : list) {
            pk.add(vo.getIbutcher());
            butMap.put(vo.getIbutcher(), vo);
            vo.setCount(count);
            List<ButcherMenuVo> menuList= mapper.selButcherMenuAndPk(vo.getIbutcher());
            vo.setMenuList(menuList);
        }
        List<ButcherPicsVo> pics = mapper.selButcherShopPicList(pk);
        for (ButcherPicsVo pic : pics) {
            butMap.get(pic.getIbutcher()).getPics().add(pic.getPic());
        }
        return list;
    }



    public ButcherShopDetailVo getShopDetail(int ibutcher) {
        if(mapper.selButcherEntity(ibutcher) == null) {
            throw new RestApiException(AuthErrorCode.VALID_SHOP);
        }
        int i;
        try {
            i= authenticationFacade.getLoginUserPk();
        } catch(Exception e) {
            i= 0;
        }
        ButDto dto= new ButDto(i, ibutcher);
        ButcherShopDetailVo vo = mapper.selButcherShopDetail(dto);
        List<DetailMenu> menus = mapper.selMenuDetail(ibutcher);
        List<Integer> menuPkList= mapper.selButcherMenu(ibutcher);
        vo.setMenus(menus);
        List<String> shopPics = mapper.selButcherShopPics(ibutcher);
        vo.setPics(shopPics);
        List<ReviewDetail> reviews = mapper.selReviewDetail(ibutcher);
        List<Integer> ireview = new ArrayList<>();
        Map<Integer, ReviewDetail> map= new HashMap<>();
        for(ReviewDetail review: reviews) {
            ireview.add(review.getIreview());
            map.put(review.getIreview(), review);
        }
        List<ReviewPicVo> pics = mapper.selReviewPicDetail(ibutcher);
        for (ReviewPicVo pic : pics) {
            map.get(pic.getIreview()).getPics().add(pic.getPic());
        }
        vo.setReviews(reviews);
        return vo;
    }

    public ResVo toggleButcherBookmark(ButcherBookmarkDto dto) {
        ButcherEntity entity = mapper.selButcherEntity(dto.getIbutcher());
        dto.setIuser(authenticationFacade.getLoginUserPk());
        if (entity == null) {
            throw new RestApiException(VALID_SHOP);
        }
        dto.setOn(mapper.selButcherBookmark(dto) == null);
        if(dto.isOn()) {
            mapper.butcherBookmarkOn(dto);
            return new ResVo(Const.ON);
        } else {
            mapper.butcherBookmarkOff(dto);
            return new ResVo(Const.OFF);
        }
    }
}
