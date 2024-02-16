package com.green.gogiro.butchershop;


import com.green.gogiro.butchershop.model.*;
import com.green.gogiro.common.Const;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.coyote.BadRequestException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "정육점",description = "정육점 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/butcher-shop")
public class ButcherShopController {
    private final ButcherShopService service;


    @GetMapping
    @Operation(summary = "정육점 리스트 보기",description = "정육점 리스트 보기 처리"+
            "<br>--요청데이터"+
            "<br>page(페이지)(default:1):기본값 1 <br>search(검색)(default:'  '):검색어 없는 것이 기본"+
            "<br>filter(필터링 구분)(default:0): <br>0:등록순, <br>1:별점순, <br>2:북마크순"+
            "<br>--응답데이터" +
            "<br>(성공)<br>ButcherSelVo의 객체 List<br>ibutcher(정육점pk), name(상호명), location(위치), count(북마크 수), pics(사진), menuList(메뉴pk&이름 리스트)"+
            "<br>(실패)")
    public List<ButcherSelVo> getButList(@Valid ButcherSelDto dto){
        return service.getButList(dto);
    }



    @GetMapping("/{ibutcher}")
    @Operation(summary = "정육점 상세 보기",description = "정육점 상세 보기 처리"+
            "<br>--요청데이터"+
            "<br>ibutcher(정육점pk)"+
            "<br>--응답데이터"+
            "<br>(성공)ibutcher(정육점pk), name(상호명), location(위치), open(영업시간), tel(전화번호), x(경도), y(위도), star(별점),"+
            "<br>isBook(로그인 사용자 북마크 확인), pics(가게 사진), menus(상세 메뉴), reviews(정육점 리뷰)"+
            "<br>(실패)")
    public ButcherShopDetailVo getButDetail(@Valid @PathVariable int ibutcher){
        return service.getShopDetail(ibutcher);
    }

    @PostMapping("/bookmark")
    @Operation(summary = "정육점 북마크 ON/OFF",description = "정육점 북마크 토글 처리"+
            "<br>--요청데이터"+
            "<br>ibutcher(정육점pk)"+
            "<br>--응답데이터"+
            "<br>(성공)result:0(북마크 OFF), 1(북마크 ON)"+
            "<br>(실패)")
    public ResVo toggleButcherBookmark(@Valid @RequestBody ButcherBookmarkDto dto) {
        return service.toggleButcherBookmark(dto);
    }

}
