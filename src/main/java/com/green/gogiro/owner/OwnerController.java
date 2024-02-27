package com.green.gogiro.owner;

import com.green.gogiro.common.ResVo;
import com.green.gogiro.owner.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "가게 주인", description = "가게 주인 API")
@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
@Slf4j
public class OwnerController {
    private final OwnerService service;

    @PostMapping(value = "/signup", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "가게 주인 회원 가입", description = "<h2>가게 주인 회원 가입 처리</h2><br>--요구 데이터<br>id:아이디(최대 20자)<br>upw:비밀번호<br>checkpw:비밀번호 확인" +
            "<br>number: 사업자 등록 번호(사이트에서 쓸 일 없으면 db에 저장할 필요 없을 듯?)<br>name: 주인의 실명<br>shopName:가게 이름<br>x:경도" +
            "<br>y:위도<br>location:위치(가게 주인이 직접 입력하는)<br>imeat:고기 종류(0:정육점, 1:돼지, 2:소, 3:닭, 4:오리, 5:양)" +
            "<br>pic: 가게 사진(일단 회원가입할 때 1장만 넣게 하죠? 리스트나 상세 정보에서 나타날 수 있게)<br>--응답 데이터" +
            "<br>(성공)result: 가게 주인pk(사용자랑 같은 테이블이기 때문에 다른 주소에서 쓸 때도 유저pk명(iuser)과 동일할 수 있습니다)" +
            "<br>(실패)에러는 나중에 로직 다 짜고 나서 해도 괜찮을까요? ㅜㅜ")
//    public ResVo ownerSignup(@RequestPart MultipartFile pic,
//                             @RequestPart String id, @RequestPart String upw, @RequestPart String checkPw,
//                             @RequestPart String num, @RequestPart String name, @RequestPart String shopName,
//                             @RequestPart String x, @RequestPart String y, @RequestPart String location,
//                             @RequestPart int imeat){
    public ResVo ownerSignup(@RequestPart MultipartFile pic, @RequestPart OwnerSignupDto dto) {
        return service.ownerSignup(pic, dto);
    }

    @GetMapping("/reservation")
    @Operation(summary = "고깃집 예약 내역 및 노쇼 내역", description = "<h2>고깃집 예약 내역 및 노쇼 내역</h2><h3>--응답 데이터<br>" +
            "--OwnerNewReservationVo(예약 내역)(배열)<br>ireser: 예약pk<br>iuser: 예약자pk<br>name: 예약자이름<br>date: 예약일시<br>headCount: 인원수<br>request: 요청사항<br>confirm: 예약 상태" +
            "--OwnerNoShowVo(노쇼 내역)(배열)<br>name: 예약자 이름<br>date: 예약일시<br>headCount: 인원수<br>" +
    "정육점은 OwnerNewReservationVo에 pickupList배열추가 노쇼는 배열값 null<br> count[int]:갯수<br>" +
            "ibutMenu[int]:메뉴pk<br>" +
            "ireser[int]: 예약pk<br>" +
            "menu[String]: 메뉴이름<br>")
    public OwnerSelReservationVo getReservation() {
        return service.getReservation();
    }


    @PostMapping("/signin")
    @Operation(summary = "가게 주인 로그인", description = "<h2>가게 주인 로그인 처리</h2>" +
            "<h3>---요구 데이터<br>email: 아이디<br>upw: 비밀번호</h3>" +
            "<h3>--응답 데이터<br>ishop: 가게pk<br>iuser: 유저pk<br>checkShop: (0: 고기집, 1:정육점)<br>accessToken: 엑세스 토큰")
    public OwnerSigninVo ownerSignin(HttpServletResponse res, @RequestBody OwnerSigninDto dto) {
        return service.ownerSignin(res, dto);
    }

    @GetMapping("/review")
    @Operation(summary = "매장 리뷰 관리", description = "<h2>매장 리뷰 관리 처리</h2>" +
            "<h3>--요구 데이터<br>페이지(sort는 신경 안쓰셔도 되요)<br>--응답 데이터<br>checkShop: 가게구분(0:고기,1:정육)<br>ireview:리뷰pk<br>ishop: 가게pk<br>iuser: 작성자 pk<br>star:별점<br>exist:사장님 댓글여부(0:댓글없음,1:댓글있음)comment:사장님 댓글<br>review: 리뷰내용<br>createdAt:작성 날짜<br>updatedAt:사장님 댓글 작성 날짜<br>pics: 리뷰 사진(배열)")
    public List<OwnerReviewVo> getAllReview(Pageable pageable) {
        return service.getAllReview(pageable);
    }
//    @PostMapping("/shop")
//    @Operation(summary = "가게 등록",description = "가게 등록 처리")
//    public StoreRegistrationPicsVo insRegistration(@RequestPart(required = false) List<MultipartFile> pics, @RequestPart StoreRegistrationDto dto){
//        dto.setPics(pics);
//        if (dto.getPics() == null || dto.getPics().isEmpty()) {
//            throw new RestApiException(MUST_PHOTO);
//        }
//        return service.insRegistration(dto);
//    }

    @GetMapping("/menu")
    @Operation(summary = "고기집 or 정육점 가게 메뉴 보기", description = "<h2>메뉴 보기 처리</h2>")
    public List<OwnerMenuVo> getMenu() {
        return service.getMenu();
    }

//    @PostMapping(value = "/menu", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @Operation(summary = "가게 메뉴 등록", description = "가게 메뉴 등록 처리")
//    public ShopMenuPicsVo insShopMenu(@RequestPart(required = false) MultipartFile pic, @RequestPart ShopMenuDto dto) {
//        if (pic != null) {
//            dto.setPic(pic);
//        }
//        return service.insShopMenu(dto);
//    }

    @PutMapping(value = "/modify", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "매장 정보 관리(수정)", description = "<h2>매장 정보 수정 처리</h2><h3>--request<br>pics: 가게사진<br>imeat:고기종류<br>name상호명: String<br>location상세주소: String<br>open매장오픈시간: String<br>tel매장전화번호: String<br>x매장주소(다음포스트)경도: String<br>y매장주소(다음포스트)위도: String<br>deposit예약금 : int")
    public OwnerManagementModifyVo updModify(@RequestPart(required = false) List<MultipartFile> pics, @RequestPart OwnerManagementModifyDto dto) {
        return null;
    }

    @GetMapping("/management")
    @Operation(summary = "매장 정보", description = "<h2>매장 정보 보기 처리</h2><h3>--응답 데이터<br>pics: 가게사진<br>imeat:고기종류<br>name상호명: String<br>location상세주소: String<br>open매장오픈시간: String<br>tel매장전화번호: String<br>x매장주소(다음포스트)경도: String<br>y매장주소(다음포스트)위도: String<br>deposit예약금 : int")
    public OwnerManagementVo getShop() {
        return service.getShop();
    }

    @PutMapping("/review")
    @Operation(summary = "고객이 작성한 리뷰에 코멘트 달기", description = "고객이 작성한 리뷰에 코멘트 처리<br>" +
            "--요청데이터<br>" +
            "ireview : 댓글pk<br>" +
            "checkshop : 0번 고기집, 1번 정육점<br>" +
            "comment : 사장님리뷰댓글<br>" +
            "--응답데이터<br>" +
            "result : 1번 성공, 나머지 에러")
    public ResVo postReviewComment(@RequestBody ReviewCommentDto dto) {
        return service.postReviewComment(dto);
    }


    @GetMapping("/dashboard")
    @Operation(summary = "대쉬 보드",description = "<h2>대쉬 보드 보기 처리</h2>" + "<h3>순서대로 배열안에 순서대로 1,2,3,4주차--응답데이터bokkmarkCnt(List:Integer):북마크 통계<br>reviewCnt:리뷰 통계<br>reservationCnt: 예약 통계<br>starAvg:총 별점")
    public DashBoardVo selDashBoard(){
        return service.selDashBoard();
    }

    //    @PutMapping("/shop")
//    @Operation(summary = "가게 사진 수정",description = "가게 사진 수정 처리")
//    public ShopPicsVo updShopPics(@RequestPart(required = false) List<MultipartFile> pics, @RequestPart ShopUpdDto dto){
//        if(pics != null) {
//            dto.setFiles(pics);
//        }
//        return service.updShopPics(dto);
//    }
//
//    @PutMapping(value = "/shop/menu",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @Operation(summary = "가게 메뉴 사진 등록", description = "가게 메뉴 사진 등록 처리")
//    public ShopMenuPicsVo updShopMenu(@RequestPart(required = false) MultipartFile pic, @RequestPart ShopMenuUpdDto dto) {
//        if (pic != null) {
//            dto.setPic(pic);
//        }
//        return service.updShopMenu(dto);
//    }

    @PutMapping(value = "menu",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "정육점 or 고기집 메뉴 수정",description = "<h3>정육점 or 고기집 메뉴 수정 처리</h3>" +
            "--요청데이터<br>" +
            "imenu(필수) : 메뉴pk<br>" +
            "menu : 메뉴이름(string)<br>" +
            "price : 가격<br>" +
            "imenu외에 값을 안 보내면 기존 값으로 돌아감<br>" +
            "--응답데이터<br>" +
            "checkshop : 0번 고기집, 1번 정육점<br>" +
            "imenu : 메뉴pk<br>" +
            "ishop : 가게pk<br>" +
            "pic : 사진"
    )
    private OwnerMenuUpdVo updMenu(@RequestPart(required = false) MultipartFile pic,@RequestPart OwnerMenuUpdDto dto) {
        return service.updMenu(pic,dto);
    }

    @PostMapping(value = "menu",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "정육점 or 고기집 메뉴 등록",description = "정육점 or 고기집 메뉴 등록 처리<br>" +
            "--요청데이터<br>" +
            "menu : 메뉴이름<br>" +
            "price : 가격<br>" +
            "pic : 사진<br>" +
            "--응답데이터<br>" +
            "imenu : 메뉴pk<br>" +
            "price : 가격<br>" +
            "pic : 사진")
    private InsMenuVo postMenu(@RequestPart(required = false) MultipartFile pic,@RequestPart OwnerMenuInsDto dto){
        return service.postMenu(pic,dto);
    }

//    @PostMapping("/butcher-shop")
//    @Operation(summary = "정육점 등록",description = "정육점 가게 등록 처리")
//    public ButcherPicVo postButcherShop(@RequestPart(required = false) List<MultipartFile> pics, @RequestPart ButcherInsDto dto){
//        dto.setFiles(pics);
//        if (dto.getPics() == null || dto.getPics().isEmpty()) {
//            throw new RestApiException(MUST_PHOTO);
//        }
//        return service.insButcherShop(dto);
//    }
//
//    @PostMapping("/butcher-shop/menu")
//    @Operation(summary = "정육점 메뉴 등록", description = "정육점 메뉴 등록 처리")
//    public ButcherMenuPicVo postButcherMenu(@RequestPart(required = false) MultipartFile pic, @RequestPart ButcherMenuInsDto dto){
//        if(pic != null) {
//            dto.setPic(pic);
//        }
//        return service.insButcherMenu(dto);
//    }
//
//    @PutMapping("/butcher-shop/menu")
//    @Operation(summary = "정육점 메뉴 사진 등록", description = "정육점 메뉴 사진 등록 처리")
//    public ButcherMenuPicVo updButcherMenu(@RequestPart(required = false) MultipartFile pic, @RequestPart ButcherMenuUpdDto dto) {
//        if(pic != null) {
//            dto.setFile(pic);
//        }
//        return service.updButcherMenu(dto);
//    }
//
//    @PutMapping("/butcher-shop")
//    @Operation(summary = "정육점 가게 사진 변경", description = "정육점 사진 변경 처리")
//    public ButcherPicVo putButcherPic(@RequestPart(required = false) List<MultipartFile> pics, @RequestPart ButcherPicsUpdDto dto) {
//        if(pics!=null){
//            dto.setFiles(pics);
//        }
//        return service.updButcherPic(dto);
//    }
}
