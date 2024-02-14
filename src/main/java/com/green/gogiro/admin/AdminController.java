package com.green.gogiro.admin;


import com.green.gogiro.admin.model.*;
import com.green.gogiro.exception.RestApiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.green.gogiro.exception.AuthErrorCode.MUST_PHOTO;

@Tag(name = "관리자", description = "관리자 API")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService service;

    @PostMapping("/shop")
    @Operation(summary = "가게 등록",description = "가게 등록 처리")
    public StoreRegistrationPicsVo insRegistration(@RequestPart(required = false) List<MultipartFile> pics, @RequestPart StoreRegistrationDto dto){
        dto.setPics(pics);
        if (dto.getPics() == null || dto.getPics().isEmpty()) {
            throw new RestApiException(MUST_PHOTO);
        }
        return service.insRegistration(dto);
    }

    @PostMapping("/shop/menu")
    @Operation(summary = "가게 메뉴 등록", description = "가게 메뉴 등록 처리")
    public ShopMenuPicsVo insShopMenu(@RequestPart(required = false) MultipartFile pic, @RequestPart ShopMenuDto dto){
        if(pic != null) {
            dto.setPic(pic);
        }
        return service.insShopMenu(dto);
    }

    @PutMapping("/shop")
    @Operation(summary = "가게 사진 수정",description = "가게 사진 수정 처리")
    public ShopPicsVo updShopPics(@RequestPart(required = false) List<MultipartFile> pics,@RequestPart ShopUpdDto dto){
        if(pics != null) {
            dto.setFiles(pics);
        }
        return service.updShopPics(dto);
    }

    @PutMapping("/shop/menu")
    @Operation(summary = "가게 메뉴 사진 등록", description = "가게 메뉴 사진 등록 처리")
    public ShopMenuPicsVo updShopMenu(@RequestPart(required = false) MultipartFile pic, @RequestPart ShopMenuUpdDto dto) {
        if(pic != null) {
            dto.setPic(pic);
        }
        return service.updShopMenu(dto);
    }

    @PostMapping("/butcher-shop")
    @Operation(summary = "정육점 등록",description = "정육점 가게 등록 처리")
    public ButcherPicVo postButcherShop(@RequestPart(required = false) List<MultipartFile> pics, @RequestPart ButcherInsDto dto){
        dto.setFiles(pics);
        if (dto.getPics() == null || dto.getPics().isEmpty()) {
            throw new RestApiException(MUST_PHOTO);
        }
        return service.insButcherShop(dto);
    }

    @PostMapping("/butcher-shop/menu")
    @Operation(summary = "정육점 메뉴 등록", description = "정육점 메뉴 등록 처리")
    public ButcherMenuPicVo postButcherMenu(@RequestPart(required = false) MultipartFile pic, @RequestPart ButcherMenuInsDto dto){
        if(pic != null) {
            dto.setPic(pic);
        }
        return service.insButcherMenu(dto);
    }

    @PutMapping("/butcher-shop/menu")
    @Operation(summary = "정육점 메뉴 사진 등록", description = "정육점 메뉴 사진 등록 처리")
    public ButcherMenuPicVo updButcherMenu(@RequestPart(required = false) MultipartFile pic, @RequestPart ButcherMenuUpdDto dto) {
        if(pic != null) {
            dto.setFile(pic);
        }
        return service.updButcherMenu(dto);
    }

    @PutMapping("/butcher-shop")
    @Operation(summary = "정육점 가게 사진 변경", description = "정육점 사진 변경 처리")
    public ButcherPicVo putButcherPic(@RequestPart(required = false) List<MultipartFile> pics, @RequestPart ButcherPicsUpdDto dto) {
        if(pics!=null){
            dto.setFiles(pics);
        }
        return service.updButcherPic(dto);
    }
}
