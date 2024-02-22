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
    @Operation(summary = "고기집 리스트 보기",description = "고기집 리스트 보기 처리" +
            "<br>--요청데이터" +
            "<br>page(페이지)(default:1):기본값 1 <br>search(검색)(default:'  '):검색어 없는 것이 기본"+
            "<br>category(고기 분류)(default:0): <br>0:모든 고기 검색, <br>1:돼지고기, <br>2:소, <br>3:닭, <br>4:오리, <br>5:양"+
            "<br>filter(필터링 구분)(default:0): <br>0:등록순, <br>1:별점순, <br>2:북마크순"+
            "<br>--응답데이터"+
            "<br>(성공)<br>ShopSelVo의 객체 List<br>ishop(고기집pk), name(상호명), location(위치), count(북마크 수), pics(사진), facilities(편의시설)"+
            "<br>(실패)")
    public List<ShopSelVo> getShopList(ShopSelDto dto) {
        return service.getShopList(dto);
    }

    @GetMapping("/{ishop}")
    @Operation(summary = "고기집 상세 보기",description = "고기집 상세 보기 처리"+
    "<br>--요청데이터"+
    "<br>ishop(고기집pk)"+
    "<br>--응답데이터"+
    "<br>(성공)ishop(고기집pk), name(상호명), location(위치), open(영업시간), tel(전화번호), x(경도), y(위도), star(별점),"+
    "<br>isBook(로그인 사용자 북마크 확인), facilities(편의 시설), pics(가게 사진), menus(상세 메뉴), reviews(고기집 리뷰)"+
    "<br>(실패)")
    public ShopDetailVo getShopDetail(@PathVariable int ishop){
        return service.getShopDetail(ishop);
    }


    @PostMapping("/bookmark")
    @Operation(summary = "고기집 북마크 ON/OFF",description = "고기집 북마크 토글 처리"+
    "<br>--요청데이터"+
    "<br>ishop(고기집pk)"+
    "<br>--응답데이터"+
    "<br>(성공)result:0(북마크 OFF), 1(북마크 ON)"+
    "<br>(실패)")
    public ResVo toggleShopBookmark(@RequestBody ShopBookmarkDto dto) {
        return service.toggleShopBookmark(dto);
    }

    @GetMapping("/main")
    @Operation(summary = "메인페이지", description = "메인페이지 처리"+
    "<br>--요청데이터"+
    "<br>--응답데이터"+
    "<br>(성공)<br>ShopMainGoGiVo객체의 List<br>객체의 데이터들<br>(ishop(고기집pk), name(상호명), pic(고기집 사진), menu(고기집 메뉴), price(메뉴 가격))"+
    "<br>ShopMainCommuVo객체 List<br>객체의 데이터들<br>(iboard(커뮤니티pk), pic(커뮤니티 사진))"+
    "<br>(실패)")
    public ShopMainVo selMainCommunity() {
        return service.selMainPage();
    }


}
