package com.green.gogiro.butchershop;

import com.green.gogiro.butchershop.model.*;
import com.green.gogiro.common.Const;
import com.green.gogiro.common.MyFileUtils;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.butcher.ButcherBookmarkEntity;
import com.green.gogiro.entity.butcher.ButcherBookmarkIds;
import com.green.gogiro.entity.butcher.ButcherEntity;
import com.green.gogiro.entity.butcher.repository.ButcherBookmarkRepository;
import com.green.gogiro.entity.butcher.repository.ButcherRepository;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.security.AuthenticationFacade;
import com.green.gogiro.user.UserMapper;
import com.green.gogiro.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static com.green.gogiro.exception.AuthErrorCode.NOT_CONTENT;

@Service
@RequiredArgsConstructor
public class ButcherShopService {
    private final ButcherShopMapper mapper;
    private final UserMapper userMapper;
    private final ButcherBookmarkRepository bookmarkRepository;
    private final ButcherRepository butcherRepository;
    private final UserRepository userRepository;

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
        if (list.size() != 0) {
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
        }
        return list;
    }



    public ButcherShopDetailVo getShopDetail(int ibutcher) {
        if(mapper.selButcherEntity(ibutcher) == null) {
            throw new RestApiException(AuthErrorCode.VALID_SHOP);
        }
        long i;
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
            review.setExist(review.getComment() != null ? 1 : 0);
            map.put(review.getIreview(), review);
        }
        List<ReviewPicVo> pics = mapper.selReviewPicDetail(ibutcher);
        for (ReviewPicVo pic : pics) {
            map.get(pic.getIreview()).getPics().add(pic.getPic());
        }
        vo.setReviews(reviews);
        return vo;
    }

//    public ResVo toggleButcherBookmark(ButcherBookmarkDto dto) {
//        ButcherModel entity = mapper.selButcherEntity(dto.getIbutcher());
//        dto.setIuser(authenticationFacade.getLoginUserPk());
//        if (entity == null) {
//            throw new RestApiException(VALID_SHOP);
//        }
//        dto.setOn(mapper.selButcherBookmark(dto) == null);
//        if(dto.isOn()) {
//            mapper.butcherBookmarkOn(dto);
//            return new ResVo(Const.ON);
//        } else {
//            mapper.butcherBookmarkOff(dto);
//            return new ResVo(Const.OFF);
//        }
//    }

    public ResVo toggleButcherBookmark(ButcherBookmarkDto dto) {
        ButcherBookmarkIds ids = new ButcherBookmarkIds();
        ids.setIuser((long)authenticationFacade.getLoginUserPk());
        ids.setIbutcher((long)dto.getIbutcher());

        AtomicInteger atomic = new AtomicInteger(0);
        bookmarkRepository.findById(ids).ifPresentOrElse(entity -> bookmarkRepository.delete(entity), () -> {
            atomic.set(1);
                    ButcherBookmarkEntity saveButcherBookmarkEntity = new ButcherBookmarkEntity();
                    saveButcherBookmarkEntity.setButcherBookmarkIds(ids);
                    UserEntity userEntity = userRepository.getReferenceById(authenticationFacade.getLoginUserPk());
                    ButcherEntity butcherEntity = butcherRepository.getReferenceById((long)dto.getIbutcher());
                    saveButcherBookmarkEntity.setUserEntity(userEntity);
                    saveButcherBookmarkEntity.setButcherEntity(butcherEntity);
                    bookmarkRepository.save(saveButcherBookmarkEntity);
                }
                );
        return new ResVo(atomic.get());

    }
}
