package com.green.gogiro.user;


import com.green.gogiro.common.ResVo;
import com.green.gogiro.user.model.ReservationVo;
import com.green.gogiro.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Tag(name = "유저 기능", description = "유저 기능 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final UserService service;


    @PostMapping(value = "/signup",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "사용자 회원 가입", description = "<h2>사용자 회원 가입 처리</h2><h3>--요청 데이터<br>pic(프로필 사진): 폼 데이터 형식" +
            "<br>email(이메일)(NOT NULL): 이메일 형식을 만족해야 한다<br>upw(비밀번호)(NOT NULL): 빈 칸 불가" +
            "<br>checkUpw(비밀번호 확인)(NOT NULL): 빈 칸 불가<br>name(실명)(NOT NULL): 빈 칸 불가<br>nickname(닉네임)(NOT NULL): 1~10글자" +
            "<br>birth(생년월일)(NOT NULL): 19991111(ex) <br>gender(성별)(NOT NULL): '남' 혹은 '여'<br>address(주소)(NOT NULL): 빈 칸 불가" +
            "<br>tel(전화번호)(NOT NULL):01012345678(ex)<br>--응답 데이터<br>(성공)result: 사용자 pk" +
            "<br>(400)INVALID_PARAMETER<br>REGEXP_TEL<br>DUPLICATION_EMAIL<br>NOT_PASSWORD_CHECK<br>NEED_NICK_NAME_CHECK")
    public ResVo signup(@RequestPart(required = false) MultipartFile pic,
                        @RequestPart @Valid UserSignupDto dto) {

        return service.signup(pic,dto);
    }

    @PostMapping("/signin")
    @Operation(summary = "사용자 로그인", description = "<h2>사용자 로그인 처리</h2><h3>--요청데이터<br>email(이메일)(NOT NULL): 이메일 형식을 만족해야 한다, 빈 칸 불가<br>" +
            "upw(비밀번호)(NOT NULL): 빈 칸 불가<br>--응답 데이터<br>(성공)result: 1<br>iuser: 유저pk<br>name: 실명<br>" +
            "pic: 프로필 사진 파일 이름<br>nickname: 닉네임<br>tel: 전화번호<br>birth: 생년월일<br>address:집 주소<br>gender: 성별" +
            "<br>accessToken: 엑세스 토큰<br>(404)INVALID_EXIST_USER_ID<br>INVALID_PASSWORD")
    public UserSignVo signin(HttpServletRequest req,
                             HttpServletResponse res,
                             @Valid @RequestBody UserSigninDto dto) {
        return service.signin(req, res, dto);
    }

    @PostMapping("/signout")
    @Operation(summary = "사용자 로그아웃", description = "<h2>사용자 로그아웃 처리</h2><h3>--응답데이터<br>(성공)result: 1")
    public ResVo signout(HttpServletResponse res) {
        return service.signout(res);
    }

    @GetMapping("/refresh-token")
    @Operation(summary = "리프레쉬토큰 발급", description = "<h2>리프레쉬토큰 발급 처리</h2><h3>--응답데이터<br>(성공)" +
            "result: 1<br>accessToken: 엑세스 토큰<br>(쿠키에 리프레쉬 토큰이 없거나 만료기간이 지난 경우)result: 0<br>accessToken: null")
    public UserSignVo getRefreshToken(HttpServletRequest req) {
        return service.getRefreshToken(req);
    }

    @PutMapping
    @Operation(summary = "회원정보 수정", description = "<h2>회원정보 수정 처리</h2><h3>--요청데이터" +
            "<br>pic:변경할 사진(변경하고 싶지 않으면 NULL가능)" +
            "<br>nickname(NOT NULL, 1~10글자): 닉네임(변경하고 싶지 않으면 원래 닉네임을 줘야 함), 빈 칸 불가" +
            "<br>address(NOT NULL): 주소(변경하고 싶지 않으면 원래 주소를 줘야 함), 빈 칸 불가" +
            "<br>tel(NOT NULL): 전화번호(변경하고 싶지 않으면 원래 전화번호를 줘야 함), 01012345678(ex), 빈 칸 불가")
    public ResVo putUser(@RequestPart(required = false) MultipartFile pic,
                         @Valid @RequestPart UserUpdDto dto) {
        if (pic != null) {
            dto.setFile(pic);
        }

        return service.updateUser(dto);
    }

    @GetMapping
    @Operation(summary = "유저 정보 보기", description = "<h2>유저 정보 보기 처리</h2>" +
            "<h3>--응답데이터<br>List<UserInfoVo><br>UserInfoVo의 데이터들<br>iuser:유저pk<br>email:이메일<br>name:이름<br>nickname:닉네임<br>" +
            "birth: 생년월일<br>gender:성별<br>address: 주소<br>pic: 프로필 사진 파일명<br>tel: 전화번호")
    public UserInfoVo getUserInfo() {
        return service.selUserInfo();
    }

    @GetMapping("/reservation")
    @Operation(summary = "예약 및 픽업 리스트", description = "<h2>회원이 등록한 예약 및 픽업 정보를 리스트로 처리</h2>" +
            "<h3>--요청데이터<br>page: 페이지<br>" +
            "--응답데이터<br>List<br>ReservationVo의 데이터들<br>checkShop: 가게 구분(0: 식당, 1:정육점)<br>ireser: 예약pk<br>ishop: 식당pk<br>name: 가게이름<br>" +
            "date: 예약날짜<br>request: 요청사항,<br>confirm: 예약 및 픽업 승인(0:대기, 1: 취소 2: 확정)<br>headCount: 인원 수<br>pic: 가게 사진<br>" +
            "isbook: 북마크 여부(0: 전, 1: 후)<br>count: 예약 총 갯수<br>createdAt: 예약 등록일")
    public List<ReservationVo> getReservation(UserMyPageDto dto) {
        return service.getReservation(dto);
    }

    @PostMapping("signup/{nickname}")
    @Operation(summary = "닉네임 중복 체크", description = "<h2>닉네임 중복 체크 처리</h2>")
    public ResVo checkNickName(@PathVariable String nickname) {
        return service.checkNickName(nickname);
    }

    @GetMapping("/review")

    @Operation(summary = "가게 후기 리스트", description = "<h2>회원이 작성한 후기 정보를 리스트로 처리</h2>" +
            "<h3>--응답데이터<br>List<br>ReviewVo의 데이터들<br>checkShop: 가게 구분(0: 식당, 1:정육점)<br>ireser: 예약pk<br>ishop: 식당pk<br>name: 가게이름<br>" +
            "star: 별점<br>review: 후기<br>pic: 가게 사진<br>count: 리뷰 총 갯수<br>createdAt: 작성 날짜<br>isBook: 북마크 여부(0: 전, 1: 후)<br>pics: 리뷰 사진")
    public List<ReviewVo> getUserReview(UserMyPageDto dto) {
        return service.getUserReview(dto);
    }

    @GetMapping("/bookmark")
    @Operation(summary = "북마크 리스트", description = "<h2>회원이 북마크 등록한 가게 정보를 리스트로 처리</h2>" +
    "<h3>--응답데이터<br>BookmarkShopVo<br>imeat: 고기종류pk(정육점은 0으로 나옴)<br>ishop: 식당pk<br>name: 상호명<br>location: 가게위치<br>open:영업시간<br>" +
    "tel: 전화번호<br>mtype: 고기종류<br>pic: 가게 사진<br>count: 북마크한 가게 총 횟수,facilities: 편의시설 정보(List)</h3>")
    public List<BookmarkShopVo> getUserBookmark(UserMyPageDto dto) {
        return service.getUserBookmark(dto);
    }

    @DeleteMapping("/review")
    @Operation(summary = "가게 후기 삭제", description = "<h2>회원이 작성한 가게 후기 삭제 처리</h2>" +
            "<h3>--요청데이터<br>checkShop: 가게구분(0: 고기집 1: 정육점)<br>ireview: 리뷰pk<br>" +
            "--응답데이터<br>result: 1(성공)")
    public ResVo delShopReview(@RequestBody @Valid ReviewDelDto dto) {
        return service.delShopReview(dto);
    }

    @PostMapping("review/report")
    @Operation(summary = "가게 후기 신고", description = "체크샵 까먹지말고")
    public ResVo reportShopReview(ReportDto dto) {
        return service.reportShopReview(dto);
    }



}
