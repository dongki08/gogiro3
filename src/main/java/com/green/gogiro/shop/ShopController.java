package com.green.gogiro.shop;

import com.green.gogiro.butchershop.model.ButcherBookmarkDto;
import com.green.gogiro.common.Const;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.shop.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.green.gogiro.exception.AuthErrorCode.MUST_PHOTO;
import static com.green.gogiro.exception.AuthErrorCode.SIZE_PHOTO;

@Tag(name = "고기집",description = "고기집 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop")
public class ShopController {
    private final ShopService service;

    @GetMapping
    @Operation(summary = "고기집 리스트 보기",description = "고기집 리스트 보기 처리")
    public List<ShopSelVo> getShopList(ShopSelDto dto) {
        return service.getShopList(dto);
    }

    @GetMapping("/{ishop}")
    @Operation(summary = "고기집 상세 보기",description = "고기집 상세 보기 처리")
    public ShopDetailVo getShopDetail(@PathVariable int ishop){
        return service.getShopDetail(ishop);
    }



    @PostMapping("/bookmark")
    @Operation(summary = "고기집 북마크 ON/OFF",description = "고기집 북마크 토글 처리")
    public ResVo toggleShopBookmark(@RequestBody ShopBookmarkDto dto) {
        return service.toggleShopBookmark(dto);
    }

    @GetMapping("/main")
    @Operation(summary = "메인페이지", description = "메인페이지 처리")
    public ShopMainVo selMainCommunity() {
        return service.selMainPage();
    }
}
