package com.green.gogiro.owner;

import com.green.gogiro.common.ResVo;
import com.green.gogiro.owner.model.*;
import com.green.gogiro.exception.RestApiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import static com.green.gogiro.exception.AuthErrorCode.MUST_PHOTO;

@Tag(name = "가게 주인", description = "가게 주인 API")
@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService service;

    @PostMapping
    @Operation(summary = "가게 주인 회원 가입",description = "--요구 데이터<br>id:아이디(최대 20자)<br>upw:비밀번호<br>checkpw:비밀번호 확인" +
            "<br>num: 사업자 등록 번호(사이트에서 쓸 일 없으면 db에 저장할 필요 없을 듯?)<br>name: 주인의 실명<br>shopName:가게 이름<br>x:경도" +
            "<br>y:위도<br>location:위치(가게 주인이 직접 입력하는)<br>imeat:고기 종류(0:정육점, 1:돼지, 2:소, 3:닭, 4:오리, 5:양)" +
            "<br>pic: 가게 사진(일단 회원가입할 때 1장만 넣게 하죠? 리스트나 상세 정보에서 나타날 수 있게)<br>--응답 데이터" +
            "<br>(성공)result: 가게 주인pk(사용자랑 같은 테이블이기 때문에 다른 주소에서 쓸 때도 유저pk명(iuser)과 동일할 수 있습니다)" +
            "<br>(실패)에러는 나중에 로직 다 짜고 나서 해도 괜찮을까요? ㅜㅜ")
    public ResVo ownerSignup(@RequestPart MultipartFile pic,
                             @RequestPart String id, @RequestPart String upw, @RequestPart String checkpw,
                             @RequestPart String num, @RequestPart String name, @RequestPart String shopName,
                             @RequestPart String x, @RequestPart String y, @RequestPart String location,
                             @RequestPart int imeat){
        return null;
    }
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
    public ShopPicsVo updShopPics(@RequestPart(required = false) List<MultipartFile> pics, @RequestPart ShopUpdDto dto){
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
