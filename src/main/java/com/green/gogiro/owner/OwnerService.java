package com.green.gogiro.owner;


import com.green.gogiro.butchershop.ButcherShopMapper;
import com.green.gogiro.common.*;
import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.butcher.ButcherEntity;
import com.green.gogiro.entity.butcher.ButcherMenuEntity;
import com.green.gogiro.entity.butcher.ButcherPicEntity;
import com.green.gogiro.entity.butcher.ButcherReviewEntity;
import com.green.gogiro.entity.butcher.repository.ButcherMenuRepository;
import com.green.gogiro.entity.butcher.repository.ButcherRepository;
import com.green.gogiro.entity.butcher.repository.ButcherReviewRepository;
import com.green.gogiro.entity.shop.*;
import com.green.gogiro.entity.shop.repository.ShopMenuRepository;
import com.green.gogiro.entity.shop.repository.ShopRepository;
import com.green.gogiro.entity.shop.repository.ShopReviewRepository;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.exception.UserErrorCode;
import com.green.gogiro.owner.model.*;
import com.green.gogiro.security.AuthenticationFacade;
import com.green.gogiro.security.JwtTokenProvider;
import com.green.gogiro.security.MyPrincipal;

import com.green.gogiro.shop.ShopMapper;
import com.green.gogiro.user.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.green.gogiro.common.Const.*;
import static com.green.gogiro.exception.AuthErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerService {
    private final OwnerMapper mapper;
    private final ButcherShopMapper butcherShopMapper;
    private final MyFileUtils myFileUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ShopRepository shopRepository;
    private final ShopCategoryRepository categoryRepository;
    private final ButcherRepository butcherRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AppProperties appProperties;
    private final CookieUtils cookieUtils;
    private final ShopMapper shopMapper;
    private final OwnerShopReviewRepository reviewRepository;
    private final AuthenticationFacade authenticationFacade;
    private final ButcherMenuRepository butcherMenuRepository;
    private final ShopMenuRepository shopMenuRepository;
    private final ShopReviewRepository shopReviewRepository;
    private final ButcherReviewRepository butcherReviewRepository;
    private final ShopCategoryRepository shopCategoryRepository;


    //가게 리뷰 보기
    @Transactional
    public List<OwnerReviewVo> getAllReview(Pageable pageable) {
        return reviewRepository.selByReviewAll(authenticationFacade.getLoginOwnerShopPk(), authenticationFacade.getLoginOwnerCheckShop(), pageable);
    }


    //가게 정보
    @Transactional
    public OwnerManagementVo getShop() {
        long ishop = authenticationFacade.getLoginOwnerShopPk();
        OwnerManagementVo ownerManagementVo = shopRepository.selDetailShop(ishop, authenticationFacade.getLoginOwnerCheckShop());
        if (authenticationFacade.getLoginOwnerCheckShop() == 0) {
            ownerManagementVo.setFacilities(shopRepository.selFacilityByShop(ishop));
        }
        log.info("a: {}", ownerManagementVo);
        return ownerManagementVo;
    }

    //메뉴 삭제
    @Transactional
    public ResVo delMenu(long imenu) {
        int checkShop = authenticationFacade.getLoginOwnerCheckShop();
        long ishop = authenticationFacade.getLoginOwnerShopPk();
        if (checkShop == 0) {
            Optional<ShopMenuEntity> optMenu = shopMenuRepository.findByImenu(imenu);
            ShopMenuEntity shopMenuEntityCheck = optMenu.orElseThrow(() -> new RestApiException(INVALID_SHOP_MENU));
            if (shopMenuEntityCheck.getShopEntity().getIshop() != ishop || shopMenuEntityCheck.getImenu() != imenu) {
                throw new RestApiException(NOT_SHOP_MENU);
            }
            ShopMenuEntity shopMenuEntity = new ShopMenuEntity();
            shopMenuEntity.setImenu(imenu);
            shopMenuRepository.delete(shopMenuEntity);
            return new ResVo(SUCCESS);
        }
        if (checkShop == 1) {
            Optional<ButcherMenuEntity> optMenu = butcherMenuRepository.findByIbutMenu(imenu);
            ButcherMenuEntity butcherMenuEntityCheck = optMenu.orElseThrow(() -> new RestApiException(INVALID_SHOP_MENU));
            if (butcherMenuEntityCheck.getButcherEntity().getIbutcher() != ishop || butcherMenuEntityCheck.getIbutMenu() != imenu) {
                throw new RestApiException(NOT_SHOP_MENU);
            }
            ButcherMenuEntity butcherMenuEntity = new ButcherMenuEntity();
            butcherMenuEntity.setIbutMenu(imenu);
            butcherMenuRepository.delete(butcherMenuEntity);
            return new ResVo(SUCCESS);
        }
        return null;
    }

    //사장님 로그인
    @Transactional
    public OwnerSigninVo ownerSignin(HttpServletResponse res, OwnerSigninDto dto) {


        Optional<UserEntity> optEntity = userRepository.findByEmail(dto.getEmail());
        UserEntity userEntity = optEntity.orElseThrow(() -> new RestApiException(AuthErrorCode.INVALID_EXIST_USER_ID));
        String shopName = null;
        if (userEntity.getCheck() == 1) {
            throw new RestApiException(AuthErrorCode.BLACK_LOGIN);
        }
        if (!userEntity.getRole().toString().equals("OWNER")) {
            throw new RestApiException(AuthErrorCode.NOT_ROLE);
        }
        if (!passwordEncoder.matches(dto.getUpw(), userEntity.getUpw())) {
            throw new RestApiException(AuthErrorCode.INVALID_PASSWORD);
        }
        MyPrincipal mp = new MyPrincipal();
        if (userEntity.getCheckShop() == 0) {
            ShopEntity entity = shopRepository.findByUserEntity(userEntity);
            if (entity.getConfirm() != 1) {
                throw new RestApiException(CONFIRM);
            }
            mp.setIuser(userEntity.getIuser());
            mp.setRole(userEntity.getRole().toString());
            mp.setCheckShop(userEntity.getCheckShop());
            mp.setIshop(entity.getIshop());
            mp.setCheckShop(userEntity.getCheckShop());
            shopName = entity.getName();

        }
        if (userEntity.getCheckShop() == 1) {
            ButcherEntity entity = butcherRepository.findByUserEntity(userEntity);
            if (entity.getConfirm() != 1) {
                throw new RestApiException(CONFIRM);
            }
            mp.setIuser(userEntity.getIuser());
            mp.setRole(userEntity.getRole().toString());
            mp.setIshop(entity.getIbutcher());
            mp.setCheckShop(userEntity.getCheckShop());
            shopName = entity.getName();
        }
        String at = jwtTokenProvider.generateAccessToken(mp);
        String rt = jwtTokenProvider.generateRefreshToken(mp);

//rt를 cookie에 담는다
        int rtCookieMaxAge = appProperties.getJwt().getRefreshTokenCookieMaxAge();
        cookieUtils.deleteCookie(res, "rt");
        cookieUtils.setCookie(res, "rt", rt, rtCookieMaxAge);
        return OwnerSigninVo.builder()
                .result(SUCCESS)
                .accessToken(at)
                .iuser(userEntity.getIuser())
                .checkShop(userEntity.getCheckShop())
                .ishop(mp.getIshop())
                .shopName(shopName)
                .build();
    }


    //가게 대쉬보드보기
    public DashBoardVo selDashBoard() {
        DashBoardDto dto = new DashBoardDto();
        dto.setCheckShop(authenticationFacade.getLoginOwnerCheckShop());
        dto.setIshop(authenticationFacade.getLoginOwnerShopPk());
        return DashBoardVo.builder()
                .bookmarkCnt(mapper.bookmarkCount(dto))
                .reviewCnt(mapper.reviewCount(dto))
                .reservationCnt(mapper.reservationCount(dto))
                .starAvg(mapper.starAvg(dto))
                .build();
    }

    //고깃집 노쇼 내역 보기
    public OwnerSelNoShowVo selNoShow(int page) {
        if (page < 1) {
            throw new RestApiException(AuthErrorCode.INVALID_PAGE);
        }
        OwnerSelNoShowVo vo = new OwnerSelNoShowVo();
        if (authenticationFacade.getLoginOwnerCheckShop() == 0) {
            LimitIdx dto = new LimitIdx();
            dto.setIshop(authenticationFacade.getLoginOwnerShopPk());
            dto.setStartIdx((page - 1) * NO_SHOW_PAGE);
            dto.setRowCount(NO_SHOW_PAGE);
            List<SelShopNoShowProcVo> pVo = mapper.selShopNoShow(dto);
            vo.setCount(mapper.selNoShowCount(dto.getIshop()));
            vo.setOwnerNoShowList(pVo);
            return vo;
        }
        return vo;
    }


    //가게 예약 내역
    public OwnerSelReservationVo getReservation(int page) {
        if (page < 1) {
            throw new RestApiException(AuthErrorCode.INVALID_PAGE);
        }
        OwnerSelReservationVo vo = new OwnerSelReservationVo();
        List<SelButcherPickupMenuProcVo> menuList;
        LimitIdx dto = new LimitIdx();
        dto.setIshop(authenticationFacade.getLoginOwnerShopPk());
        dto.setStartIdx((page - 1) * RESERVATION_PAGE);
        dto.setRowCount(RESERVATION_PAGE);
        int checkShop = authenticationFacade.getLoginOwnerCheckShop();
        List<OwnerNewReservationVo> voList;
        List<Integer> pk = new ArrayList<>();
        HashMap<Integer, OwnerNewReservationVo> map = new HashMap<>();
        if (checkShop == 0) {
            vo.setCheckShop(checkShop);
            voList = mapper.selShopReservation(dto);
            vo.setOwnerReservationList(voList);
            vo.setCount(mapper.selReservationCount(dto.getIshop()));
            return vo;
        }
        if (checkShop == 1) {
            vo.setCheckShop(checkShop);
            voList = mapper.selButcherPickup(dto);
            if (voList.isEmpty()) {
                return vo;
            }
            menuList = mapper.selButcherPickupMenu(dto);
            vo.setOwnerReservationList(voList);
            for (OwnerNewReservationVo reservationVo : voList) {
                pk.add(reservationVo.getIreser().intValue());
                map.put(reservationVo.getIreser().intValue(), reservationVo);
            }
            for (SelButcherPickupMenuProcVo vo1 : menuList) {
                map.get(vo1.getIreser()).getPickupList().add(vo1);
            }
            vo.setOwnerReservationList(voList);
            vo.setCount(mapper.selPickupCount(dto.getIshop()));
            return vo;
        }
        return null;
    }


    //사장님 회원가입
    @Transactional
    public ResVo ownerSignup(MultipartFile pic, OwnerSignupDto dto) {
        if (!dto.getUpw().equals(dto.getCheckPw())) {
            throw new RestApiException(UserErrorCode.NOT_PASSWORD_CHECK);
        }
        String hashedPw = passwordEncoder.encode(dto.getUpw());
        UserEntity entity = new UserEntity();
        entity.setAddress(dto.getLocation());
        if (dto.getImeat() > 0) {
            entity.setCheckShop(0);
        } else {
            entity.setCheckShop(1);
        }
        entity.setUpw(hashedPw);
        entity.setEmail(dto.getId());
        entity.setName(dto.getName());
        entity.setRole(RoleEnum.OWNER);
        entity.setNickname(dto.getShopName());
        userRepository.save(entity);
        UserEntity userEntity = userRepository.getReferenceById(entity.getIuser());
        if (entity.getCheckShop() == 0) {
            ShopEntity shopEntity = new ShopEntity();
            ShopCategoryEntity shopCategoryEntity = categoryRepository.getReferenceById(dto.getImeat());
            shopEntity.setUserEntity(userEntity);
            shopEntity.setShopCategoryEntity(shopCategoryEntity);
            shopEntity.setLocation(dto.getLocation());
            shopEntity.setX(dto.getX());
            shopEntity.setY(dto.getY());
            shopEntity.setNumber(dto.getNum());
            shopEntity.setName(dto.getShopName());
            shopRepository.save(shopEntity);
            String target = "/shop/" + shopEntity.getIshop() + "/shop_pic" + "/";
            StoreRegistrationPicsVo vo = new StoreRegistrationPicsVo();
            String saveFileNm = myFileUtils.transferTo(pic, target);
            vo.getPics().add(saveFileNm);

            List<ShopPicEntity> shopPicEntityList = vo.getPics().stream().map(item -> ShopPicEntity.builder()
                    .shopEntity(shopEntity)
                    .pic(item)
                    .build()).collect(Collectors.toList());
            shopEntity.getShopPicEntityList().addAll(shopPicEntityList);
            return new ResVo(userEntity.getIuser().intValue());
        }
        if (entity.getCheckShop() == 1) {
            ButcherEntity butcherEntity = new ButcherEntity();
            butcherEntity.setUserEntity(userEntity);
            butcherEntity.setLocation(dto.getLocation());
            butcherEntity.setX(dto.getX());
            butcherEntity.setY(dto.getY());
            butcherEntity.setNumber(dto.getNum());
            butcherEntity.setName(dto.getShopName());
            butcherRepository.save(butcherEntity);
            String target = "/butcher/" + butcherEntity.getIbutcher() + "/butchershop_pic" + "/";
            ButcherPicVo vo = new ButcherPicVo();
            String saveFileNm = myFileUtils.transferTo(pic, target);
            vo.getPics().add(saveFileNm);
            List<ButcherPicEntity> butcherPicEntityList = vo.getPics().stream().map(item -> ButcherPicEntity.builder()
                    .pic(item)
                    .butcherEntity(butcherEntity)
                    .build()).toList();
            butcherEntity.getButcherPicEntityList().addAll(butcherPicEntityList);
            return new ResVo(userEntity.getIuser().intValue());
        }
        return new ResVo(Const.FAIL);
    }

    //가게 메뉴보기
    @Transactional
    public List<OwnerMenuVo> getMenu() {
        log.info("pk: {}", authenticationFacade.getLoginOwnerShopPk());
        return butcherMenuRepository.selMenu(authenticationFacade.getLoginOwnerShopPk(), authenticationFacade.getLoginOwnerCheckShop());
    }


    //가게 수정
    @Transactional
    public OwnerManagementModifyVo updModify(List<MultipartFile> pics, OwnerManagementModifyDto dto) {
        int checkShop = authenticationFacade.getLoginOwnerCheckShop();
        long ishop = authenticationFacade.getLoginOwnerShopPk();
        OwnerManagementModifyVo mVo = new OwnerManagementModifyVo();
        if (checkShop == 0) {
            List<OwnerShopPicsProcVo> vo = shopMapper.selByShopPics((int) ishop);
            ShopCategoryEntity categoryEntity = categoryRepository.getReferenceById(dto.getImeat());
            mVo.setIshop(ishop);
            mVo.setCheckShop(checkShop);
            int picsSize = vo != null ? vo.size() : 0;
            int fileSize = pics != null ? pics.size() : 0;
            int delSize = dto.getIshopPics() != null ? dto.getIshopPics().size() : 0;
            int totalSize = picsSize + fileSize - delSize;
            if (totalSize > 5) {
                throw new RestApiException(SIZE_PHOTO);
            }
            ShopEntity shopEntity = shopRepository.getReferenceById(ishop);
            if (dto.getName().isEmpty() || Pattern.matches(REGEXP_PATTERN_SPACE_CHAR,dto.getName())) {
                shopEntity.setName(shopEntity.getName());
            } else {
                shopEntity.setName(dto.getName());
            }

            if(dto.getTel().isEmpty() || Pattern.matches(REGEXP_PATTERN_SPACE_CHAR,dto.getTel())) {
                shopEntity.setTel(shopEntity.getTel());
            } else {
                shopEntity.setTel(dto.getTel());
            }
            if (dto.getLocation().isEmpty() || Pattern.matches(REGEXP_PATTERN_SPACE_CHAR,dto.getLocation())) {
                shopEntity.setLocation(shopEntity.getLocation());
            } else {
                shopEntity.setLocation(dto.getLocation());
            }
            if(dto.getOpen().isEmpty() || Pattern.matches(REGEXP_PATTERN_SPACE_CHAR,dto.getOpen())){
                shopEntity.setOpen(shopEntity.getOpen());
            } else {
                shopEntity.setOpen(dto.getOpen());
            }
            if (dto.getX().isEmpty() || Pattern.matches(REGEXP_PATTERN_SPACE_CHAR,dto.getX())) {
                shopEntity.setX(shopEntity.getX());
            } else {
                shopEntity.setX(dto.getX());
            }
            if (dto.getY().isEmpty() || Pattern.matches(REGEXP_PATTERN_SPACE_CHAR,dto.getY())) {
                shopEntity.setY(shopEntity.getY());
            } else {
                shopEntity.setY(dto.getY());
            }
            if (dto.getImeat() != 0) {
                shopCategoryRepository.findByImeat(dto.getImeat()).orElseThrow(() -> new RestApiException(INVALID_CATEGORY));
            }
            if (dto.getImeat() == 0) {
                throw new RestApiException(NOT_SHOP_0);
            }
            shopEntity.setShopCategoryEntity(categoryEntity);

            if (dto.getDeposit() == null) {
                shopEntity.setDeposit(shopEntity.getDeposit());
            } else {
                shopEntity.setDeposit(dto.getDeposit());
            }
            String target = "/shop/" + ishop + "/shop_pic" + "/";
            ShopUpdDto pDto = new ShopUpdDto();
            pDto.setIshop((int) ishop);
            if (dto.getIshopPics() != null && !dto.getIshopPics().isEmpty()) {
                List<ShopSelPicsNumDto> picList = mapper.selShopPics(dto.getIshopPics());
                if (picList.isEmpty()) {
                    throw new RestApiException(INVALID_PIC);
                }
                for (ShopSelPicsNumDto pic : picList) {
                    if (pic.getIshop() != ishop) {
                        throw new RestApiException(NOT_PIC);
                    }
                    myFileUtils.delFolderTrigger2(target + pic.getPic());
                }
                mapper.delShopPics(dto.getIshopPics());
            }
            if (pics != null && !pics.isEmpty()) {
                for (MultipartFile file : pics) {
                    String saveFileNm = myFileUtils.transferTo(file, target);
                    pDto.getPics().add(saveFileNm);
                }
                mVo.setPics(pDto.getPics());
                mapper.insShopPic(pDto);
            }
            if (!dto.getFacilities().isEmpty()) {
                shopMapper.delFacilities(ishop);
                shopMapper.insFacilities(ishop, dto.getFacilities());
            }
            shopRepository.save(shopEntity);
            return mVo;
        }
        if (checkShop == 1) {
            if (dto.getImeat() != 0) {
                throw new RestApiException(INVALID_BUTCHER_CATEGORY);
            }
            List<OwnerShopPicsProcVo> vo = butcherShopMapper.selByButcherShopPics(ishop);
            mVo.setIshop(ishop);
            mVo.setCheckShop(BUTCHER_CHECK_NUM);
            int picsSize = vo != null ? vo.size() : 0;
            int fileSize = pics != null ? pics.size() : 0;
            int delSize = dto.getIshopPics() != null ? dto.getIshopPics().size() : 0;
            int totalSize = picsSize + fileSize - delSize;
            if (totalSize > 5) {
                throw new RestApiException(SIZE_PHOTO);
            }
            ButcherEntity butcherEntity = butcherRepository.getReferenceById(ishop);
            if (dto.getName().isEmpty() || Pattern.matches(REGEXP_PATTERN_SPACE_CHAR,dto.getName())) {
                butcherEntity.setName(butcherEntity.getName());
            } else {
                butcherEntity.setName(dto.getName());
            }
            if(dto.getTel().isEmpty() || Pattern.matches(REGEXP_PATTERN_SPACE_CHAR,dto.getTel())) {
                butcherEntity.setTel(butcherEntity.getTel());
            } else {
                butcherEntity.setTel(dto.getTel());
            }
            if(dto.getOpen().isEmpty() || Pattern.matches(REGEXP_PATTERN_SPACE_CHAR,dto.getOpen())){
                butcherEntity.setOpen(butcherEntity.getOpen());
            } else {
                butcherEntity.setOpen(dto.getOpen());
            }
            if (dto.getLocation().isEmpty() || Pattern.matches(REGEXP_PATTERN_SPACE_CHAR,dto.getLocation())) {
                butcherEntity.setLocation(butcherEntity.getLocation());
            } else {
                butcherEntity.setLocation(dto.getLocation());
            }
            if (dto.getX().isEmpty() || Pattern.matches(REGEXP_PATTERN_SPACE_CHAR,dto.getX())) {
                butcherEntity.setX(butcherEntity.getX());
            } else {
                butcherEntity.setX(dto.getX());
            }
            if (dto.getY().isEmpty() || Pattern.matches(REGEXP_PATTERN_SPACE_CHAR,dto.getY())) {
                butcherEntity.setY(butcherEntity.getY());
            } else {
                butcherEntity.setY(dto.getY());
            }
            String path = "/butcher/" + ishop + "/butchershop_pic" + "/";
            ButcherInsDto pDto = new ButcherInsDto();
            pDto.setIbutcher((int) ishop);

            if (dto.getIshopPics() != null && !dto.getIshopPics().isEmpty()) {
                List<ButcherPicsProcVo> picList = mapper.selButcherPics(dto.getIshopPics());
                if (picList.isEmpty()) {
                    throw new RestApiException(INVALID_PIC);
                }
                for (ButcherPicsProcVo pic : picList) {
                    if (pic.getIshop() != ishop) {
                        throw new RestApiException(NOT_PIC);
                    }
                    myFileUtils.delFolderTrigger2(path + pic.getPic());
                }
                mapper.delButcherPics(dto.getIshopPics());
                if (pics != null && !pics.isEmpty()) {
                    for (MultipartFile pic : pics) {
                        String saveFileNm = myFileUtils.transferTo(pic, path);
                        pDto.getPics().add(saveFileNm);
                    }
                    mVo.setPics(pDto.getPics());
                    mapper.insButcherPics(pDto);
                }
            }
            butcherRepository.save(butcherEntity);
            return mVo;
        }
        return null;
    }


    //메뉴 수정
    @Transactional
    public OwnerMenuUpdVo updMenu(MultipartFile pic, OwnerMenuUpdDto dto) {
        int checkShop = authenticationFacade.getLoginOwnerCheckShop();
        long ishop = authenticationFacade.getLoginOwnerShopPk();
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setIshop(ishop);
        ButcherEntity butcherEntity = new ButcherEntity();
        butcherEntity.setIbutcher(ishop);
        String savedName = null;
        if (checkShop == 0) {
            ShopMenuEntity shopMenuEntity = shopMenuRepository.getReferenceById(dto.getImenu());
            shopMenuEntity.setImenu(dto.getImenu());
            shopMenuEntity.setShopEntity(shopEntity);
            if (pic != null) {
                myFileUtils.delFolderTrigger2(shopMenuEntity.getPic());
                String target = "/shop/" + ishop + "/menu" + "/";
                savedName = myFileUtils.transferTo(pic, target);
                shopMenuEntity.setPic(savedName);
            }
            if (dto.getMenu().isEmpty() || Pattern.matches(REGEXP_PATTERN_SPACE_CHAR,dto.getMenu())) {
                shopMenuEntity.setMenu(shopMenuEntity.getMenu());
            } else {
                shopMenuEntity.setMenu(dto.getMenu());
            }
            if (dto.getPrice() == null) {
                shopMenuEntity.setPrice(shopMenuEntity.getPrice());
            } else {
                shopMenuEntity.setPrice(dto.getPrice());
            }
            shopMenuRepository.save(shopMenuEntity);
            return OwnerMenuUpdVo.builder()
                    .checkShop(checkShop)
                    .imenu(shopMenuEntity.getImenu().intValue())
                    .ishop(ishop)
                    .pic(savedName)
                    .build();
        }
        if (checkShop == 1) {
            ButcherMenuEntity butcherMenuEntity = butcherMenuRepository.getReferenceById(dto.getImenu());
            butcherMenuEntity.setButcherEntity(butcherEntity);
            if (pic != null) {
                myFileUtils.delFolderTrigger2(butcherMenuEntity.getPic());
                String target = "/butcher/" + ishop + "/menu" + "/";
                savedName = myFileUtils.transferTo(pic, target);
                butcherMenuEntity.setPic(savedName);
            }
            if (dto.getMenu().isEmpty() || Pattern.matches(REGEXP_PATTERN_SPACE_CHAR,dto.getMenu())) {
                butcherMenuEntity.setMenu(butcherMenuEntity.getMenu());
            } else {
                butcherMenuEntity.setMenu(dto.getMenu());
            }

            if (dto.getPrice() == null) {
                butcherMenuEntity.setPrice(butcherMenuEntity.getPrice());
            } else {
                butcherMenuEntity.setPrice(dto.getPrice());
            }
            butcherMenuRepository.save(butcherMenuEntity);
            return OwnerMenuUpdVo.builder()
                    .ishop(ishop)
                    .imenu(butcherMenuEntity.getIbutMenu().intValue())
                    .pic(savedName)
                    .checkShop(checkShop)
                    .build();
        }
        return null;
    }


    //가게 메뉴 등록
    @Transactional
    public InsMenuVo postMenu(MultipartFile pic, OwnerMenuInsDto dto) {
        int checkShop = authenticationFacade.getLoginOwnerCheckShop();
        long ishop = authenticationFacade.getLoginOwnerShopPk();
        ShopEntity shopEntity = new ShopEntity();
        ButcherEntity butcherEntity = new ButcherEntity();
        if (checkShop == 0) {
            shopEntity.setIshop(ishop);
            ShopMenuEntity entity = new ShopMenuEntity();
            entity.setShopEntity(shopEntity);
            //고깃집 메뉴 사진 업로드
            if (pic != null) {
                String target = "/shop/" + ishop + "/menu" + "/";
                String savedName = myFileUtils.transferTo(pic, target);
                entity.setPic(savedName);
            }
            entity.setMenu(dto.getMenu());
            if (dto.getPrice() != 0) {
                entity.setPrice(dto.getPrice());
            }
            shopMenuRepository.save(entity);
            return InsMenuVo.builder()
                    .pic(entity.getPic())
                    .imenu(entity.getImenu())
                    .price(entity.getPrice())
                    .build();
        }
        if (checkShop == 1) {
            butcherEntity.setIbutcher(ishop);
            ButcherMenuEntity entity = new ButcherMenuEntity();
            entity.setButcherEntity(butcherEntity);
            //정육점 메뉴 사진 업로드
            if (pic != null) {
                String target = "/butcher/" + ishop + "/menu" + "/";
                String savedName = myFileUtils.transferTo(pic, target);
                entity.setPic(savedName);
            }
            entity.setMenu(dto.getMenu());
            if (dto.getPrice() != 0) {
                entity.setPrice(dto.getPrice());
            }
            butcherMenuRepository.save(entity);
            return InsMenuVo.builder()
                    .pic(entity.getPic())
                    .price(entity.getPrice())
                    .imenu(entity.getIbutMenu())
                    .build();
        }
        return null;
    }


    //리뷰 댓글 달기
    @Transactional
    public ResVo postReviewComment(ReviewCommentDto dto) {
        if (dto.getCheckShop() == 0) {
            Optional<ShopReviewEntity> optReview = Optional.of(shopReviewRepository.getReferenceById((long) dto.getIreview()));
            ShopReviewEntity shopReviewEntity = optReview.orElseThrow(() -> new RestApiException(AuthErrorCode.NOT_CONTENT));
            if (shopReviewEntity.getShopEntity().getIshop() != authenticationFacade.getLoginOwnerShopPk()) {
                throw new RestApiException(AuthErrorCode.NOT_SHOP);
            }
            shopReviewEntity.setComment(dto.getComment());
            shopReviewRepository.save(shopReviewEntity);
            return new ResVo(Const.SUCCESS);
        } else {
            Optional<ButcherReviewEntity> optReview = Optional.of(butcherReviewRepository.getReferenceById((long) dto.getIreview()));
            ButcherReviewEntity butcherReviewEntity = optReview.orElseThrow(() -> new RestApiException(AuthErrorCode.NOT_CONTENT));
            butcherReviewEntity.setComment(dto.getComment());
            butcherReviewRepository.save(butcherReviewEntity);
            return new ResVo(Const.SUCCESS);
        }
    }
}


//
//    public StoreRegistrationPicsVo insRegistration(StoreRegistrationDto dto) {
//
//        if (dto.getPics().size() > 5) {
//            throw new RestApiException(AuthErrorCode.SIZE_PHOTO);
//        }
//        mapper.insStoreRegistration(dto);
//        String target = "/shop/" + dto.getIshop() + "/shop_pic";
//        StoreRegistrationPicsVo vo = new StoreRegistrationPicsVo();
//        vo.setIshop(dto.getIshop());
//
//        for (MultipartFile file : dto.getPics()) {
//            String saveFileNm = myFileUtils.transferTo(file, target);
//            vo.getPics().add(saveFileNm);
//        }
//        if (dto.getIfacil() != null || dto.getIfacil().get(0) != 0) {
//            mapper.insShopFacility(dto);
//        }
//        mapper.insStoreRegistrationPics(vo);
//        return vo;
//    }

//    public ShopPicsVo updShopPics(ShopUpdDto dto) {
//        if (dto.getFiles() != null && dto.getFiles().size() > 5) {
//            throw new RestApiException(AuthErrorCode.SIZE_PHOTO);
//        }
//        String target = "/shop/" + dto.getIshop() + "/shop_pic";
//        if (dto.getIshopPics() != null && !dto.getIshopPics().isEmpty()) {
//            List<ShopSelPicsNumDto> sDto = mapper.selShopPics(dto.getIshopPics());
//            for (ShopSelPicsNumDto pics : sDto) {
//                myFileUtils.delFolderTrigger2(target + "/" + pics.getPic());
//            }
//            mapper.delShopPics(dto.getIshopPics());
//        }
//        if (dto.getFiles() != null) {
//            for (MultipartFile file : dto.getFiles()) {
//                String saveFileNm = myFileUtils.transferTo(file, target);
//                dto.getPics().add(saveFileNm);
//            }

//mapper.insShopPic(dto);
//        }
//        ShopPicsVo vo = new ShopPicsVo();
//        vo.setIshop(dto.getIshop());
//        vo.setPics(dto.getPics());
//        return vo;
//    }

//    public ShopMenuPicsVo insShopMenu(ShopMenuDto dto) {
//
//        String target = "/shop/" + dto.getIshop() + "/menu";
//        String fileNm = myFileUtils.transferTo(dto.getPic(), target);
//        dto.setFileNm(fileNm);
//        mapper.insShopMenu(dto);
//        ShopMenuPicsVo vo = new ShopMenuPicsVo();
//        vo.setIshop(dto.getIshop());
//        vo.setPic(fileNm);
//        vo.setImenu(dto.getImenu());
//        return vo;
//    }

//    public ShopMenuPicsVo updShopMenu(ShopMenuUpdDto dto) {
//        String picNm = mapper.selPicNm(dto.getImenu());
//        log.info("picNm: {}", picNm);
//        String target = "/shop/" + dto.getIshop() + "/menu";
//        myFileUtils.delFolderTrigger2(target + "/" + picNm);
//
//
//        String fileNm = myFileUtils.transferTo(dto.getPic(), target);
//        dto.setFileNm(fileNm);
//        mapper.updShopMenu(dto);
//        ShopMenuPicsVo vo = new ShopMenuPicsVo();
//        vo.setIshop(dto.getIshop());
//        vo.setPic(fileNm);
//        vo.setImenu(dto.getImenu());
//        return vo;
//    }

//    @Transactional
//    public ButcherPicVo insButcherShop(ButcherInsDto dto) {
//        mapper.insButcherShop(dto);
//
//        if (dto.getFiles().size() > 5) {
//            throw new RestApiException(AuthErrorCode.SIZE_PHOTO);
//        }
//        String target = "/butcher/" + dto.getIbutcher() + "/butchershop_pic";
//        ButcherPicVo vo = new ButcherPicVo();
//        vo.setIbutcher(dto.getIbutcher());
//
//        for (MultipartFile file : dto.getFiles()) {
//            String saveFileNm = myFileUtils.transferTo(file, target);
//            vo.getPics().add(saveFileNm);
//        }
//        mapper.insButcherPics(dto);
//        return vo;
//    }
//
//    public ButcherMenuPicVo insButcherMenu(ButcherMenuInsDto dto) {
//        String target = "/butcher/" + dto.getIbutcher() + "/menu";
//        String fileNm = myFileUtils.transferTo(dto.getPic(), target);
//        dto.setFileNm(fileNm);
//        mapper.insButcherMenu(dto);
//        ButcherMenuPicVo vo = new ButcherMenuPicVo();
//        vo.setIbutcher(dto.getIbutcher());
//        vo.setPic(fileNm);
//        vo.setIbutMenu(dto.getIbutMenu());
//        return vo;
//    }
//
//    public ButcherMenuPicVo updButcherMenu(ButcherMenuUpdDto dto) {
//        String picNm = mapper.selButcherMenuPicNm(dto.getIbutMenu());
//        String target = "/butcher/" + dto.getIbutcher() + "/menu";
//        myFileUtils.delFolderTrigger2(target + "/" + picNm);
//
//        String fileNm = myFileUtils.transferTo(dto.getFile(), target);
//        dto.setFileNm(fileNm);
//        mapper.updButcherMenu(dto);
//        ButcherMenuPicVo vo = new ButcherMenuPicVo();
//        vo.setIbutcher(dto.getIbutcher());
//        vo.setPic(fileNm);
//        vo.setIbutMenu(dto.getIbutMenu());
//        return vo;
//    }

//    public ButcherPicVo updButcherPic(ButcherPicsUpdDto dto) {
//        if (dto.getFiles() != null && dto.getFiles().size() > 5) {
//            throw new RestApiException(AuthErrorCode.SIZE_PHOTO);
//        }
//        String path = "/butcher/" + dto.getIbutcher() + "/butchershop_pic";
//        if (dto.getIbutPics() != null && !dto.getIbutPics().isEmpty()) {
//            List<ButcherPicsProcVo> picList = mapper.selButcherPics(dto.getIbutPics());
//            if (!picList.isEmpty()) {
//                for (ButcherPicsProcVo vo : picList) {
//                    myFileUtils.delFolderTrigger2(path + "/" + vo.getPic());
//                }
//                mapper.delButcherPics(dto.getIbutPics());
//            }
//        }
//        if (dto.getFiles() != null) {
//            for (MultipartFile pic : dto.getFiles()) {
//                String fileNm = myFileUtils.transferTo(pic, path);
//                dto.getPics().add(fileNm);
//            }
//            mapper.insButcherPics(dto);
//        }
//
//
//        ButcherPicVo vo = new ButcherPicVo();
//        vo.setIbutcher(dto.getIbutcher());
//        vo.setPics(dto.getPics());
//
//        return vo;
//    }

