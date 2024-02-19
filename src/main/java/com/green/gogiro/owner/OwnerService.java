package com.green.gogiro.owner;

import com.green.gogiro.butchershop.ButcherRepository;
import com.green.gogiro.common.Const;
import com.green.gogiro.common.MyFileUtils;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.common.RoleEnum;
import com.green.gogiro.entity.UserEntity;
import com.green.gogiro.entity.butcher.ButcherEntity;
import com.green.gogiro.entity.butcher.ButcherPicEntity;
import com.green.gogiro.entity.shop.ShopCategoryEntity;
import com.green.gogiro.entity.shop.ShopEntity;
import com.green.gogiro.entity.shop.ShopPicEntity;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.exception.UserErrorCode;
import com.green.gogiro.owner.model.*;
import com.green.gogiro.shop.ShopRepository;
import com.green.gogiro.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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

    @Transactional
    public ResVo ownerSignup(List<MultipartFile> pics, OwnerSignupDto dto) {
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
            ShopCategoryEntity shopCategoryEntity = new ShopCategoryEntity();
            shopCategoryEntity = categoryRepository.getReferenceById(dto.getImeat());
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
            for (MultipartFile file : pics) {
                String saveFileNm = myFileUtils.transferTo(file, target);
                vo.getPics().add(saveFileNm);
            }
            List<ShopPicEntity> shopPicEntityList = vo.getPics().stream().map(item -> ShopPicEntity.builder()
                    .ishop(shopEntity)
                    .pic(item)
                    .build()).collect(Collectors.toList());
            shopEntity.getShopPicEntityList().addAll(shopPicEntityList);
            return new ResVo(shopEntity.getIshop().intValue());
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
            for (MultipartFile file : pics) {
                String saveFileNm = myFileUtils.transferTo(file, target);
                vo.getPics().add(saveFileNm);
            }
            List<ButcherPicEntity> butcherPicEntityList = vo.getPics().stream().map(item -> ButcherPicEntity.builder()
                    .pic(item)
                    .butcherEntity(butcherEntity)
                    .build()).collect(Collectors.toList());
            butcherEntity.getButcherPicEntityList().addAll(butcherPicEntityList);
            return new ResVo(butcherEntity.getIbutcher().intValue());
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
}
