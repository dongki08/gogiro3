package com.green.gogiro.owner;


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
import com.green.gogiro.shop.model.ShopFacilityVo;
import com.green.gogiro.shop.model.ShopModel;
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
import java.util.stream.Collectors;

import static com.green.gogiro.exception.AuthErrorCode.SIZE_PHOTO;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerService {
    private final OwnerMapper mapper;
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


    //가게 리뷰 보기
    @Transactional
    public List<OwnerReviewVo> getAllReview(Pageable pageable) {
        List<OwnerReviewVo> vo = reviewRepository.selByReviewAll(authenticationFacade.getLoginOwnerShopPk(), authenticationFacade.getLoginOwnerCheckShop(), pageable);
        return vo;
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


    //사장님 로그인
    @Transactional
    public OwnerSigninVo ownerSignin(HttpServletResponse res, OwnerSigninDto dto) {


        Optional<UserEntity> optEntity = userRepository.findByEmail(dto.getEmail());
        UserEntity userEntity = optEntity.orElseThrow(() -> new RestApiException(AuthErrorCode.INVALID_EXIST_USER_ID));
        if (!userEntity.getRole().toString().equals("OWNER")) {
            throw new RestApiException(AuthErrorCode.NOT_ROLE);
        }
        if (!passwordEncoder.matches(dto.getUpw(), userEntity.getUpw())) {
            throw new RestApiException(AuthErrorCode.INVALID_PASSWORD);
        }
        MyPrincipal mp = new MyPrincipal();
        if (userEntity.getCheckShop() == 0) {
            ShopEntity entity = shopRepository.findByUserEntity(userEntity);
            mp.setIuser(userEntity.getIuser());
            mp.setRole(userEntity.getRole().toString());
            mp.setCheckShop(userEntity.getCheckShop());
            mp.setIshop(entity.getIshop());
            mp.setCheckShop(userEntity.getCheckShop());

        }
        if (userEntity.getCheckShop() == 1) {
            ButcherEntity entity = butcherRepository.findByUserEntity(userEntity);
            mp.setIuser(userEntity.getIuser());
            mp.setRole(userEntity.getRole().toString());
            mp.setIshop(entity.getIbutcher());
            mp.setCheckShop(userEntity.getCheckShop());
        }
        String at = jwtTokenProvider.generateAccessToken(mp);
        String rt = jwtTokenProvider.generateRefreshToken(mp);

//rt를 cookie에 담는다
        int rtCookieMaxAge = appProperties.getJwt().getRefreshTokenCookieMaxAge();
        cookieUtils.deleteCookie(res, "rt");
        cookieUtils.setCookie(res, "rt", rt, rtCookieMaxAge);
        return OwnerSigninVo.builder()
                .accessToken(at)
                .iuser(userEntity.getIuser())
                .checkShop(userEntity.getCheckShop())
                .ishop(mp.getIshop())
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


    //가게 예약 내역 or 노쇼 내역 보기
    public OwnerSelReservationVo getReservation() {
        OwnerSelReservationVo vo = new OwnerSelReservationVo();
        long ishop = authenticationFacade.getLoginOwnerShopPk();
        List<SelButcherPickupMenuProcVo> menuList = new ArrayList<>();
        int checkShop = authenticationFacade.getLoginOwnerCheckShop();
        List<OwnerNewReservationVo> voList = new ArrayList<>();
        List<Integer> pk = new ArrayList<>();
        HashMap<Integer, OwnerNewReservationVo> map = new HashMap<>();
        if (checkShop == 0) {
            vo.setCheckShop(checkShop);
            voList = mapper.selShopReservation(ishop);
            List<SelShopNoShowProcVo> noShowVo = mapper.selShopNoShow(ishop);
            vo.setOwnerReservationList(voList);
            vo.setOwnerNoShowList(noShowVo);
            return vo;
        }
        if (checkShop == 1) {
            vo.setCheckShop(checkShop);
            voList = mapper.selButcherPickup(ishop);
            menuList = mapper.selButcherPickupMenu(ishop);
            vo.setOwnerReservationList(voList);
            for (OwnerNewReservationVo reservationVo : voList) {
                pk.add(reservationVo.getIreser().intValue());
                map.put(reservationVo.getIreser().intValue(), reservationVo);
            }
            for (SelButcherPickupMenuProcVo vo1 : menuList) {
                map.get(vo1.getIreser()).getPickupList().add(vo1);
            }
            vo.setOwnerReservationList(voList);
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
            String target = "/shop/" + shopEntity.getIshop() + "/shop_pic";
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
            String target = "/butcher/" + butcherEntity.getIbutcher() + "/butchershop_pic";
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
    public OwnerManagementModifyVo updModify(List<MultipartFile> pics, OwnerManagementModifyDto dto) {
        int checkShop = authenticationFacade.getLoginOwnerCheckShop();
        long ishop = authenticationFacade.getLoginOwnerShopPk();
        if (checkShop == 0) {
            ShopModel model = shopMapper.selShopEntity((int) ishop);
            List<OwnerShopPicsProcVo> vo = shopMapper.selByShopPics((int) ishop);
            OwnerManagementModifyVo mVo = new OwnerManagementModifyVo();

            int picsSize = vo != null ? vo.size() : 0;
            int fileSize = pics != null ? pics.size() : 0;
            int delSize = dto.getIshopPics() != null ? dto.getIshopPics().size() : 0;
            int totalSize = picsSize + fileSize - delSize;
            if (totalSize > 5) {
                throw new RestApiException(SIZE_PHOTO);
            }
            if (checkShop == 0) {
                ShopEntity shopEntity = shopRepository.getReferenceById(ishop);
                if (dto.getName().isEmpty()) {
                    shopEntity.setName(shopEntity.getName());
                } else {
                    shopEntity.setName(dto.getName());
                }
                if (dto.getLocation().isEmpty()) {
                    shopEntity.setLocation(shopEntity.getLocation());
                } else {
                    shopEntity.setLocation(dto.getLocation());
                }
                if (dto.getX().isEmpty()) {
                    shopEntity.setX(shopEntity.getX());
                } else {
                    shopEntity.setX(dto.getX());
                }
                if (dto.getY().isEmpty()) {
                    shopEntity.setY(shopEntity.getY());
                } else {
                    shopEntity.setY(dto.getY());
                }
                if (dto.getImeat() == null) {
                    shopEntity.getShopCategoryEntity().setImeat(shopEntity.getShopCategoryEntity().getImeat());
                } else {
                    shopEntity.getShopCategoryEntity().setImeat(dto.getImeat());
                }
                if (dto.getDeposit() == null) {
                    shopEntity.setDeposit(shopEntity.getDeposit());
                } else {
                    shopEntity.setDeposit(dto.getDeposit());
                }
                mapper.delFacilities(ishop);
                if (!dto.getFacility().isEmpty()) {
                    mapper.insFacilities(ishop, dto.getFacility());
                }
                shopRepository.save(shopEntity);
                    String target = "/shop/" + ishop + "/shop_pic";
                    ShopUpdDto pDto = new ShopUpdDto();
                    pDto.setIshop((int)ishop);
                if (pics != null && !pics.isEmpty()) {
                    if (dto.getIshopPics() != null && !dto.getIshopPics().isEmpty()) {
                        List<ShopSelPicsNumDto> sDto = mapper.selShopPics(dto.getIshopPics());
                        for (ShopSelPicsNumDto pic : sDto) {
                            myFileUtils.delFolderTrigger2(target + "/" + pic.getPic());
                        }
                        mapper.delShopPics(dto.getIshopPics());
                    }
                        for (MultipartFile file : pics) {
                            String saveFileNm = myFileUtils.transferTo(file, target);
                            pDto.getPics().add(saveFileNm);
                            mVo.getPics().add(saveFileNm);
                        }
                        mapper.insShopPic(pDto);
                        mVo.setIshop(ishop);
                        mVo.setCheckShop(checkShop);
                        return mVo;
                    }
                }
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
                String target = "/shop/" + ishop + "/menu";
                savedName = myFileUtils.transferTo(pic, target);
                shopMenuEntity.setPic(savedName);
            }
            if (dto.getMenu().isEmpty()) {
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
                String target = "/butcher/" + ishop + "/menu";
                savedName = myFileUtils.transferTo(pic, target);
                butcherMenuEntity.setPic(savedName);
            }
            if (dto.getMenu() == null) {
                butcherMenuEntity.setMenu(butcherMenuEntity.getMenu());
            }
            if (dto.getPrice() == null) {
                butcherMenuEntity.setPrice(butcherMenuEntity.getPrice());

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
//            mapper.insShopPic(dto);
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
            if (pic != null) {
                String target = "/shop/" + ishop + "/menu";
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
            if (pic != null) {
                String target = "/butcher/" + ishop + "/butchershop_pic";
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
