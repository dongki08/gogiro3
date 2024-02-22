package com.green.gogiro.owner;


import com.green.gogiro.common.*;
import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.butcher.ButcherEntity;
import com.green.gogiro.entity.butcher.ButcherPicEntity;
import com.green.gogiro.entity.butcher.ButcherReviewEntity;
import com.green.gogiro.entity.butcher.repository.ButcherRepository;
import com.green.gogiro.entity.butcher.repository.ButcherReviewRepository;
import com.green.gogiro.entity.shop.*;
import com.green.gogiro.entity.shop.repository.ShopRepository;
import com.green.gogiro.entity.shop.repository.ShopReviewRepository;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.exception.UserErrorCode;
import com.green.gogiro.owner.model.*;
import com.green.gogiro.security.AuthenticationFacade;
import com.green.gogiro.security.JwtTokenProvider;
import com.green.gogiro.security.MyPrincipal;

import com.green.gogiro.user.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final OwnerShopReviewRepository reviewRepository;
    private final AuthenticationFacade authenticationFacade;
    private final ShopReviewRepository shopReviewRepository;
    private final ButcherReviewRepository butcherReviewRepository;


    @Transactional
    public List<OwnerReviewVo> getAllReview(Pageable pageable) {
        List<OwnerReviewVo> vo = reviewRepository.selByReviewAll(authenticationFacade.getLoginOwnerShopPk(), authenticationFacade.getLoginOwnerCheckShop(), pageable);
        return vo;
    }

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
            shopEntity.setImeat(shopCategoryEntity);
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

    public StoreRegistrationPicsVo insRegistration(StoreRegistrationDto dto) {

        if (dto.getPics().size() > 5) {
            throw new RestApiException(AuthErrorCode.SIZE_PHOTO);
        }
        mapper.insStoreRegistration(dto);
        String target = "/shop/" + dto.getIshop() + "/shop_pic";
        StoreRegistrationPicsVo vo = new StoreRegistrationPicsVo();
        vo.setIshop(dto.getIshop());

        for (MultipartFile file : dto.getPics()) {
            String saveFileNm = myFileUtils.transferTo(file, target);
            vo.getPics().add(saveFileNm);
        }
        if (dto.getIfacil() != null || dto.getIfacil().get(0) != 0) {
            mapper.insShopFacility(dto);
        }
        mapper.insStoreRegistrationPics(vo);
        return vo;
    }

    public ShopPicsVo updShopPics(ShopUpdDto dto) {
        if (dto.getFiles() != null && dto.getFiles().size() > 5) {
            throw new RestApiException(AuthErrorCode.SIZE_PHOTO);
        }
        String target = "/shop/" + dto.getIshop() + "/shop_pic";
        if (dto.getIshopPics() != null && !dto.getIshopPics().isEmpty()) {
            List<ShopSelPicsNumDto> sDto = mapper.selShopPics(dto.getIshopPics());
            for (ShopSelPicsNumDto pics : sDto) {
                myFileUtils.delFolderTrigger2(target + "/" + pics.getPic());
            }
            mapper.delShopPics(dto.getIshopPics());
        }
        if (dto.getFiles() != null) {
            for (MultipartFile file : dto.getFiles()) {
                String saveFileNm = myFileUtils.transferTo(file, target);
                dto.getPics().add(saveFileNm);
            }
            mapper.insShopPic(dto);
        }
        ShopPicsVo vo = new ShopPicsVo();
        vo.setIshop(dto.getIshop());
        vo.setPics(dto.getPics());
        return vo;
    }

    public ShopMenuPicsVo insShopMenu(ShopMenuDto dto) {

        String target = "/shop/" + dto.getIshop() + "/menu";
        String fileNm = myFileUtils.transferTo(dto.getPic(), target);
        dto.setFileNm(fileNm);
        mapper.insShopMenu(dto);
        ShopMenuPicsVo vo = new ShopMenuPicsVo();
        vo.setIshop(dto.getIshop());
        vo.setPic(fileNm);
        vo.setImenu(dto.getImenu());
        return vo;
    }

    public ShopMenuPicsVo updShopMenu(ShopMenuUpdDto dto) {
        String picNm = mapper.selPicNm(dto.getImenu());
        log.info("picNm: {}", picNm);
        String target = "/shop/" + dto.getIshop() + "/menu";
        myFileUtils.delFolderTrigger2(target + "/" + picNm);


        String fileNm = myFileUtils.transferTo(dto.getPic(), target);
        dto.setFileNm(fileNm);
        mapper.updShopMenu(dto);
        ShopMenuPicsVo vo = new ShopMenuPicsVo();
        vo.setIshop(dto.getIshop());
        vo.setPic(fileNm);
        vo.setImenu(dto.getImenu());
        return vo;
    }

    @Transactional
    public ButcherPicVo insButcherShop(ButcherInsDto dto) {
        mapper.insButcherShop(dto);

        if (dto.getFiles().size() > 5) {
            throw new RestApiException(AuthErrorCode.SIZE_PHOTO);
        }
        String target = "/butcher/" + dto.getIbutcher() + "/butchershop_pic";
        ButcherPicVo vo = new ButcherPicVo();
        vo.setIbutcher(dto.getIbutcher());

        for (MultipartFile file : dto.getFiles()) {
            String saveFileNm = myFileUtils.transferTo(file, target);
            vo.getPics().add(saveFileNm);
        }
        mapper.insButcherPics(dto);
        return vo;
    }

    public ButcherMenuPicVo insButcherMenu(ButcherMenuInsDto dto) {
        String target = "/butcher/" + dto.getIbutcher() + "/menu";
        String fileNm = myFileUtils.transferTo(dto.getPic(), target);
        dto.setFileNm(fileNm);
        mapper.insButcherMenu(dto);
        ButcherMenuPicVo vo = new ButcherMenuPicVo();
        vo.setIbutcher(dto.getIbutcher());
        vo.setPic(fileNm);
        vo.setIbutMenu(dto.getIbutMenu());
        return vo;
    }

    public ButcherMenuPicVo updButcherMenu(ButcherMenuUpdDto dto) {
        String picNm = mapper.selButcherMenuPicNm(dto.getIbutMenu());
        String target = "/butcher/" + dto.getIbutcher() + "/menu";
        myFileUtils.delFolderTrigger2(target + "/" + picNm);

        String fileNm = myFileUtils.transferTo(dto.getFile(), target);
        dto.setFileNm(fileNm);
        mapper.updButcherMenu(dto);
        ButcherMenuPicVo vo = new ButcherMenuPicVo();
        vo.setIbutcher(dto.getIbutcher());
        vo.setPic(fileNm);
        vo.setIbutMenu(dto.getIbutMenu());
        return vo;
    }

    public ButcherPicVo updButcherPic(ButcherPicsUpdDto dto) {
        if (dto.getFiles() != null && dto.getFiles().size() > 5) {
            throw new RestApiException(AuthErrorCode.SIZE_PHOTO);
        }
        String path = "/butcher/" + dto.getIbutcher() + "/butchershop_pic";
        if (dto.getIbutPics() != null && !dto.getIbutPics().isEmpty()) {
            List<ButcherPicsProcVo> picList = mapper.selButcherPics(dto.getIbutPics());
            if (!picList.isEmpty()) {
                for (ButcherPicsProcVo vo : picList) {
                    myFileUtils.delFolderTrigger2(path + "/" + vo.getPic());
                }
                mapper.delButcherPics(dto.getIbutPics());
            }
        }
        if (dto.getFiles() != null) {
            for (MultipartFile pic : dto.getFiles()) {
                String fileNm = myFileUtils.transferTo(pic, path);
                dto.getPics().add(fileNm);
            }
            mapper.insButcherPics(dto);
        }


        ButcherPicVo vo = new ButcherPicVo();
        vo.setIbutcher(dto.getIbutcher());
        vo.setPics(dto.getPics());

        return vo;
    }

    @Transactional
    public ResVo postReviewComment(ReviewCommentDto dto) {
        if (dto.getCheckShop() == 0) {
            Optional<ShopReviewEntity> optReview = Optional.of(shopReviewRepository.getReferenceById((long) dto.getIreview()));
            ShopReviewEntity shopReviewEntity = optReview.orElseThrow(() ->  new RestApiException(AuthErrorCode.NOT_CONTENT));
            shopReviewEntity.setComment(dto.getComment());
            shopReviewRepository.save(shopReviewEntity);
            return new ResVo(Const.SUCCESS);
        } else {
            Optional<ButcherReviewEntity> optReview = Optional.of(butcherReviewRepository.getReferenceById((long) dto.getIreview()));
            ButcherReviewEntity butcherReviewEntity = optReview.orElseThrow(() ->  new RestApiException(AuthErrorCode.NOT_CONTENT));
            butcherReviewEntity.setComment(dto.getComment());
            butcherReviewRepository.save(butcherReviewEntity);
            return new ResVo(Const.SUCCESS);
        }
    }
}
