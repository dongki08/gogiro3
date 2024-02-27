package com.green.gogiro.user;

import static com.green.gogiro.common.Const.*;
import static com.green.gogiro.exception.AuthErrorCode.REPORT_COMMUNITY_MYUSER;
import static com.green.gogiro.exception.AuthErrorCode.REPORT_REVIEW_MYUSER;

import com.green.gogiro.common.*;
import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.butcher.ButcherEntity;
import com.green.gogiro.entity.butcher.ButcherReviewCountEntity;
import com.green.gogiro.entity.butcher.ButcherReviewCountIds;
import com.green.gogiro.entity.butcher.ButcherReviewEntity;
import com.green.gogiro.entity.butcher.repository.ButcherReportRepository;
import com.green.gogiro.entity.butcher.repository.ButcherRepository;
import com.green.gogiro.entity.butcher.repository.ButcherReviewCountRepository;
import com.green.gogiro.entity.butcher.repository.ButcherReviewRepository;
import com.green.gogiro.entity.community.ReportEntity;
import com.green.gogiro.entity.shop.ShopEntity;
import com.green.gogiro.entity.shop.ShopReviewCountEntity;
import com.green.gogiro.entity.shop.ShopReviewCountIds;
import com.green.gogiro.entity.shop.ShopReviewEntity;
import com.green.gogiro.entity.shop.repository.ShopReportRepository;
import com.green.gogiro.entity.shop.repository.ShopRepository;
import com.green.gogiro.entity.shop.repository.ShopReviewCountRepository;
import com.green.gogiro.entity.shop.repository.ShopReviewRepository;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.exception.UserErrorCode;
import com.green.gogiro.security.AuthenticationFacade;
import com.green.gogiro.security.JwtTokenProvider;
import com.green.gogiro.security.MyPrincipal;
import com.green.gogiro.security.MyUserDetails;
import com.green.gogiro.shop.*;
import com.green.gogiro.shop.model.ShopFacilityVo;
import com.green.gogiro.user.model.ReservationVo;
import com.green.gogiro.user.model.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;
import java.util.regex.Pattern;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final ShopMapper shopMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AppProperties appProperties;
    private final CookieUtils cookeUtils;
    private final AuthenticationFacade authenticationFacade;
    private final MyFileUtils myFileUtils;
    private final UserRepository userRepository;
    private final ShopReportRepository shopReportRepository;
    private final ShopReviewCountRepository shopReviewCountRepository;
    private final ShopRepository shopRepository;
    private final ShopReviewRepository shopReviewRepository;
    private final ButcherRepository butcherRepository;
    private final ButcherReviewRepository butcherReviewRepository;
    private final ButcherReportRepository butcherReportRepository;
    private final ButcherReviewCountRepository butcherReviewCountRepository;


    @Transactional
    public ResVo signup(MultipartFile pic, UserSignupDto dto) {

        if (mapper.checkNickname(dto.getNickname()) != null) {
            throw new RestApiException(UserErrorCode.NEED_NICK_NAME_CHECK);
        }
        if (!dto.getUpw().equals(dto.getCheckUpw())) {
            throw new RestApiException(UserErrorCode.NOT_PASSWORD_CHECK);
        }
        String hashedPw = passwordEncoder.encode(dto.getUpw());
        dto.setUpw(hashedPw);

        if (mapper.checkEmail(dto.getEmail()) != null) {
            throw new RestApiException(UserErrorCode.DUPLICATION_EMAIL);
        }

        if ("01000000000".equals(dto.getTel())) {
            throw new RestApiException(UserErrorCode.REGEXP_TEL);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userEntity.setName(dto.getName());
        userEntity.setAddress(dto.getAddress());
        userEntity.setBirth(dto.getBirth());
        userEntity.setUpw(hashedPw);
        userEntity.setTel(dto.getTel());
        userEntity.setGender(dto.getGender());
        userEntity.setNickname(dto.getNickname());
        userEntity.setCheckShop(2);
        userEntity.setRole(RoleEnum.USER);


        if (pic != null) {
            String path = "/user/" + dto.getIuser();
            String savedPicFileNm = myFileUtils.transferTo(pic, path);
            dto.setPic(savedPicFileNm);
            mapper.updUserPic(dto);
            userEntity.setPic(dto.getPic());
        }
        userRepository.save(userEntity);
        return new ResVo(userEntity.getIuser().intValue());
    }

    public ResVo checkNickName(String nickName) {
        if (mapper.checkNickname(nickName) != null || Pattern.matches(nickName + REGEXP_PATTERN_SPACE_CHAR, nickName)) {
            throw new RestApiException(UserErrorCode.DUPLICATION_NICK_NAME);
        }
        return new ResVo(SUCCESS);
    }

    //1:성공 , 2:비밀번호 다름, 3:아이디없음
    public UserSignVo signin(HttpServletRequest req,
                             HttpServletResponse res,
                             UserSigninDto dto) {
        Optional<UserEntity> optEntity = userRepository.findByEmail(dto.getEmail());
        UserEntity userEntity = optEntity.orElseThrow(() -> new RestApiException(AuthErrorCode.INVALID_EXIST_USER_ID));
        if(!userEntity.getRole().toString().equals("USER")){
            throw new RestApiException(AuthErrorCode.NOT_ROLE);
        }
        if (!passwordEncoder.matches(dto.getUpw(), userEntity.getUpw())) {
            throw new RestApiException(AuthErrorCode.INVALID_PASSWORD);
        }
        MyPrincipal myPrincipal = MyPrincipal.builder()
                .iuser(userEntity.getIuser())
                .role(userEntity.getRole().toString())
                .build();
        String at = jwtTokenProvider.generateAccessToken(myPrincipal);
        String rt = jwtTokenProvider.generateRefreshToken(myPrincipal);

        int rtCookieMaxAge = appProperties.getJwt().getRefreshTokenCookieMaxAge();
        cookeUtils.deleteCookie(res, "rt");
        cookeUtils.setCookie(res, "rt", rt, rtCookieMaxAge);

        return UserSignVo.builder()
                .result(SUCCESS)
                .iuser(userEntity.getIuser())
                .name(userEntity.getName())
                .pic(userEntity.getPic())
                .nickname(userEntity.getNickname())
                .tel(userEntity.getTel())
                .birth(userEntity.getBirth())
                .address(userEntity.getAddress())
                .gender(userEntity.getGender())
                .accessToken(at)
                .build();
    }

    public ResVo signout(HttpServletResponse res) {
        cookeUtils.deleteCookie(res, "rt");
        return new ResVo(1);
    }

    public UserSignVo getRefreshToken(HttpServletRequest req) {
        Cookie cookie = cookeUtils.getCookie(req, "rt");
        if (cookie == null) {
            return UserSignVo.builder().
                    result(FAIL)
                    .accessToken(null)
                    .build();
        }
        String token = cookie.getValue();
        if (!jwtTokenProvider.isValidateToken(token)) {
            return UserSignVo.builder().
                    result(FAIL)
                    .accessToken(null)
                    .build();
        }
        MyUserDetails myUserDetails = (MyUserDetails) jwtTokenProvider.getUserDetailsFromToken(token);
        MyPrincipal myPrincipal = myUserDetails.getMyPrincipal();

        String at = jwtTokenProvider.generateAccessToken(myPrincipal);

        return UserSignVo.builder().
                result(SUCCESS)
                .accessToken(at)
                .build();
    }

    //유저 정보 수정
    public ResVo updateUser(UserUpdDto dto) {
        dto.setIuser((int)authenticationFacade.getLoginUserPk());

        //null이 아닌데(이미있다) 본인이 아닐 때
        if (mapper.checkNickname(dto.getNickname()) != null
                && !mapper.checkNicknameBeforeUpdate(dto.getIuser()).equals(dto.getNickname())) {
            throw new RestApiException(UserErrorCode.DUPLICATION_NICK_NAME);
        }
        if (dto.getFile() != null) {
            String path = "/user/" + dto.getIuser() + "/";
            myFileUtils.delFolderTrigger2(path);
            String savedPicFileNm = myFileUtils.transferTo(dto.getFile(), path);
            dto.setPic(savedPicFileNm);
        }
        mapper.updateUser(dto);
        return new ResVo(SUCCESS);
    }

    public UserInfoVo selUserInfo() {
        UserInfoVo vo = mapper.selUserInfo((int)authenticationFacade.getLoginUserPk());
        vo.setIuser((int)authenticationFacade.getLoginUserPk());
        return vo;
    }

    public List<ReservationVo> getReservation(UserMyPageDto dto) {
        dto.setIuser((int)authenticationFacade.getLoginUserPk());
        int count = mapper.selUserReservationCount(dto.getIuser());
        List<ReservationVo> list = mapper.selReservation(dto);
        if (!list.isEmpty()) {
            for (ReservationVo vo : list) {
                vo.setCount(count);
            }
        }
        return list;
    }

    public List<ReviewVo> getUserReview(UserMyPageDto dto) {
        dto.setIuser((int)authenticationFacade.getLoginUserPk());
        List<ReviewVo> reviews = mapper.selUserReview(dto);
        int count = mapper.selUserReviewCount(dto.getIuser());
        List<ReviewPk> reviewPkList = new ArrayList<>();
        Map<ReviewPk, ReviewVo> reviewMap = new HashMap<>();
        for (ReviewVo vo : reviews) {
            vo.setCount(count);
            ReviewPk pk = new ReviewPk(vo.getCheckShop(), vo.getIreview());
            reviewMap.put(pk, vo);
            reviewPkList.add(pk);
        }

        for (ReviewPk pk : reviewPkList) {
            List<String> pics = mapper.selUserReviewPic(pk);
            reviewMap.get(pk).setPics(pics);
        }
        return reviews;
    }

    public List<BookmarkShopVo> getUserBookmark(UserMyPageDto dto) {
        dto.setIuser((int)authenticationFacade.getLoginUserPk());
        List<BookmarkShopVo> list = mapper.selUserBookmark(dto);
        List<Integer> ishopList = new ArrayList<>();
        Map<Integer, BookmarkShopVo> shopMap = new HashMap<>();
        int count = mapper.selUserBookmarkCount(dto.getIuser());
        for (BookmarkShopVo vo : list) {
            vo.setCount(count);
            if (vo.getImeat() != 0) {
                ishopList.add(vo.getIshop());
                shopMap.put(vo.getIshop(), vo);
            }
        }

        List<ShopFacilityVo> facilityList = shopMapper.selShopFacility(ishopList);
        for (ShopFacilityVo vo : facilityList) {
            if (shopMap.get(vo.getIshop()).getImeat() != 0) {
                shopMap.get(vo.getIshop()).getFacilities().add(vo.getFacility());
            }
        }
        return list;
    }

    public ResVo delShopReview(ReviewDelDto dto) {
        dto.setIuser((int)authenticationFacade.getLoginUserPk());
        mapper.delShopReviewPics(dto);
        return new ResVo(mapper.delShopReview(dto));
    }

    // 리뷰 신고
    @Transactional
    public ResVo reportShopReview(ReportDto dto) {
        ShopReviewCountIds shopReview = new ShopReviewCountIds();
        ButcherReviewCountIds butcherReview = new ButcherReviewCountIds();

        // 고깃집 리뷰 신고
        if (dto.getCheckShop() == 0) {
            shopReview.setIuser(authenticationFacade.getLoginUserPk());
            shopReview.setIreview(dto.getIreview());
            ReportEntity shopReportEntity = shopReportRepository.getReferenceById(dto.getIreport());

            Optional<ShopReviewCountEntity> reviewCountEntity = shopReviewCountRepository.findByShopReviewCountIds(shopReview);
            if (reviewCountEntity.isPresent()) {
                throw new RestApiException(AuthErrorCode.REPORT_COMMUNITY_ENTITY);
            }

            UserEntity userEntity = userRepository.getReferenceById(authenticationFacade.getLoginUserPk());
            ShopEntity shopEntity = shopRepository.getReferenceById(dto.getIshop());
            ShopReviewEntity shopReviewEntity = shopReviewRepository.getReferenceById(dto.getIreview());

            if (shopEntity.getUserEntity().getIuser() == authenticationFacade.getLoginUserPk()) {
                throw new RestApiException(REPORT_COMMUNITY_MYUSER);
            }

            ShopReviewCountEntity shopReviewCountEntity = new ShopReviewCountEntity();
            shopReviewCountEntity.setShopReviewCountIds(shopReview);
            shopReviewCountEntity.setIreport(shopReportEntity);
            shopReviewCountEntity.setUserEntity(userEntity);
            shopReviewCountEntity.setShopReviewEntity(shopReviewEntity);
            shopReviewCountRepository.save(shopReviewCountEntity);

            shopReviewEntity.setCount(shopReviewEntity.getCount() + 1);
            shopRepository.save(shopEntity);

            shopReviewEntity.getUserEntity().setCount(shopReviewEntity.getUserEntity().getCount() + 1);

            return new ResVo(SUCCESS);
        }

        // 정육점 리뷰 신고
        else {
            butcherReview.setIuser(authenticationFacade.getLoginUserPk());
            butcherReview.setIuser(dto.getIreview());
            ReportEntity butcherReportEntity = butcherReportRepository.getReferenceById(dto.getIreport());

            Optional<ButcherReviewCountEntity> reviewCountEntity = butcherReviewCountRepository.findByButcherReviewCountIds(butcherReview);
            if (reviewCountEntity.isPresent()) {
                throw new RestApiException(AuthErrorCode.REPORT_REVIEW_ENTITY);
            }

            UserEntity userEntity = userRepository.getReferenceById(authenticationFacade.getLoginUserPk());
            ButcherEntity butcherEntity = butcherRepository.getReferenceById(dto.getIshop());
            ButcherReviewEntity butcherReviewEntity = butcherReviewRepository.getReferenceById(dto.getIreview());

            if (butcherEntity.getUserEntity().getIuser() == authenticationFacade.getLoginUserPk()) {
                throw new RestApiException(REPORT_REVIEW_MYUSER);
            }

            ButcherReviewCountEntity butcherReviewCountEntity = new ButcherReviewCountEntity();
            butcherReviewCountEntity.setButcherReviewCountIds(butcherReview);
            butcherReviewCountEntity.setIreport(butcherReportEntity);
            butcherReviewCountEntity.setUserEntity(userEntity);
            butcherReviewCountEntity.setButcherReviewEntity(butcherReviewEntity);
            butcherReviewCountRepository.save(butcherReviewCountEntity);

            butcherReviewEntity.setCount(butcherReviewEntity.getCount() + 1);
            butcherRepository.save(butcherEntity);

            butcherReviewEntity.getUserEntity().setCount(butcherReviewEntity.getUserEntity().getCount() + 1);

            return new ResVo(SUCCESS);
        }
    }

}
