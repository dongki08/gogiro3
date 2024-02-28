package com.green.gogiro.admin;



import com.green.gogiro.admin.model.*;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.exception.CommonErrorCode;
import com.green.gogiro.exception.RestApiException;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "총 관리자", description = "총 관리자 API")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService service;
    //0.총 관리자 로그인
    @PostMapping("/signin")
    @Operation(summary="총 관리자 로그인",description="--요청 데이터<br>email:아이디, upw:비밀번호" +
            "<br>--응답 데이터<br>result:로그인 결과(1:성공, 2:비밀번호틀림, 3: 아이디 없음)<br>iuser:유저pk<br>accessToken:엑세스 토큰")
    public AdminSigninVo adminSignin(HttpServletResponse res, @Valid @RequestBody AdminSigninDto dto){
        return service.adminSignin2(res,dto);
    }
    /*1.매장 관리 리스트
    1)DB에 등록된 모든 가게들(고기집+정육점)의 리스트
    2)각 가게의 가게 승인 여부 표시
    3)가게 이름 검색 기능*/
    @GetMapping("/shop")
    @Operation(summary="매장 관리 리스트",description="사이트에 등록된 가게(고기집&정육점) 리스트" +
            "<br>1)DB에 등록된 모든 가게들(고기집+정육점)의 리스트" +
            "<br>2)각 가게의 가게 승인 여부 표시<br>3)가게 이름 검색 기능<br>" +
            "<br>1.상세 주소를 위도,경도를 이용해서 부르고 싶으시면 x,y로 보내드리겠습니다" +
            "<br>2.그냥 가게 주인이 직접 쓴 주소(문자열)로 받고 싶으시면 location으로 보내드리겠습니다" +
            "<br>3.고기 종류도 나타내고 싶으시면 checkShop에 0:정육점, 1:돼지, 2:소, 3:닭, 4:오리, 5:양으로 보내드릴 수 있습니다"+
            "<br><br>--요구 데이터<br>search: 검색어(가게 이름)(필수 아님)<br>--응답 데이터<br>(성공)" +
            "<br>checkShop:가게 구분(0:고기집, 1:정육점)<br>ishop:가게 pk(최소 1 이상)" +
            "<br>name:가게 이름<br>x:경도, y:위도<br>pic:가게 사진(여러 장으로 받고 싶으시면 리스트로 변경하겠습니다)" +
            "<br>tel:전화번호<br>confirm:승인 여부(0:대기, 1:확정, 2: 거절, 3:퇴출)"+
            "<br>(실패)<br>(400)<br>INVALID_EXIST_USER_ID<br>(401)UNAUTHORIZED" +
            "<br>(404)<br>INVALID_PASSWORD<br>(500)INTERNAL_SERVER_ERROR<br>")
    public List<ShopVo> shopList(String search){
        return service.shopList1(search);
    }
    /*2.가게 승인 여부 변경
    1)대기 상태의 가게->승인 혹은 거절
    2)승인 상태의 가게->퇴출
    3)거절 상태의 가게->재승인*/
    @PatchMapping("/confirm")
    @Operation(summary="가게 승인 여부 변경",description="총 관리자의 가게 승인/거절/퇴출" +
            "<br>1)대기 상태의 가게->승인 혹은 거절<br>2)승인 상태의 가게->퇴출<br>3)거절 상태의 가게->재승인" +
            "<br>--요구 데이터<br>checkShop(0 또는 1): 가게 구분(0:고기집, 1:정육점)<br>ishop(최소 1 이상): 가게 pk" +
            "<br>confirm:변경하고 싶은 승인 여부(1:확정, 2:거절, 3:퇴출)<br>--응답 데이터<br>(성공)" +
            "<br>result: 1<br>(실패)<br>(400)<br>INVALID_PARAMETER<br>(401)UNAUTHORIZED<br>(404)VALID_SHOP<br>(500)INTERNAL_SERVER_ERROR")
    public ResVo confirmShop(@Valid @RequestBody ConfirmDto dto){return service.confirmShop2(dto);}
    /*3.신고 글 리스트(고기잡담 글, 고기잡담 글의 댓글, 고기집 후기, 정육점 후기)
    1)해당 글의 신고 수가 3회 이상이면 블러 처리 및 신고 글 리스트에 추가
    2)계정 제재는 총 관리자 재량으로*/
    @GetMapping("/report")
    @Operation(summary="신고 글 리스트",description="고기잡담 글, 고기잡담 글의 댓글, 고기집 후기, 정육점 후기 중 신고당한 글 리스트" +
            "<br>1)해당 글의 신고 수가 3회 이상이면 블러 처리 및 신고 글 리스트에 추가<br>2)계정 제재는 총 관리자 재량으로" +
            "<br>--요구 데이터<br>check:글 종류(0:고기잡담 글, 1:고기잡담 댓글, 2:고기집 후기, 3:정육점 후기)<br>" +
            "--응답 데이터<br>pk:해당 글 pk<br>contents:신고 글 내용<br>writerNm:게시물 작성자<br>count:현재 신고받은 수"+
            "<br>state:상태(실패)<br>(401)UNAUTHORIZED<br>(500)INTERNAL_SERVER_ERROR<br>")
    public List<ReportedVo> reportList(int check){
        //글 종류(0:고기잡담 글, 1:고기잡담 댓글, 2:고기집 후기, 3:정육점 후기)
        if(check<0||check>3){throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);}
        return service.reportList1(check);
    }
    /*4.신고받은 글 숨김
    1)DB에 해당 글이 있는 지 확인 후 숨김(관리자니까)
    2)글을 숨기기 전에 컨펌 창 하나 띄워주는 건 어떤가요*/
    @PatchMapping("/hide")
    @Operation(summary="글 숨김",description="총 관리자가 신고받은 글(고기잡담 글, 고기잡담 글의 댓글, 고기집 후기, 정육점 후기 중 신고당한 글)을 숨긴다" +
            "<br>1)신고가 3개 이상이 되거나 총 관리자가 글 숨김 처리를 하면 리스트나 후기에서 글이 안 보이게 된다" +
            "<br><br>--요구 데이터<br>check:글 종류(0:고기잡담 글, 1:고기잡담 댓글, 2:고기집 후기, 3:정육점 후기)" +
            "<br>pk:해당 글 pk<br>--응답 데이터<br>(성공)result: 1<br>(실패)" +
            "<br>(404)NOT_COMMUNITY_CHECK<br>(500)INTERNAL_SERVER_ERROR<br>")
    public ResVo hide(@Valid @RequestBody HideDto dto){return service.hide2(dto);}
    /*5.게시물 신고 취소
    1)신고 수 1 이상인 게시 글의 신고 수를 0으로 만든다(관리자니까)
    2)신고를 취소하기 전에 컨펌 창 하나 띄워주는 건 어떤가요?*/
    @PatchMapping("/report")
    @Operation(summary="신고 취소",description="신고받은 글(고기잡담 글, 고기잡담 글의 댓글, 고기집 후기, 정육점 후기 중 신고당한 글) 신고 취소" +
            "<br>--요청 데이터<br>check: 글 종류(0:고기잡담 글, 1:고기잡담 댓글, 2:고기집 후기, 3:정육점 후기)" +
            "<br>pk: 해당 글 pk<br>--응답 데이터<br>(성공)<br>result= 1<br>(400)INVALID_PARAMETER" +
            "<br>(404)NOT_COMMUNITY_CHECK")
    public ResVo cancelReport(@Valid @RequestBody CancelReportDto dto){return service.cancelReport2(dto);}
    /*6.사용자(USER,OWNER)블랙 리스트(정지)
    1)신고 글 리스트에 오른 글의 작성자
    2)노쇼 카운트 2회 이상인 사용자*/
    @GetMapping("/black")
    @Operation(summary="계정 관리 리스트",description="신고받거나 정지된 USER(이용자),OWNER(가게 주인) 리스트" +
            "<br>2.이름 부분을 실명으로 하고 싶으신지 닉네임으로 하고 싶으신지 궁금합니다<br><br>--요구 데이터: 없음" +
            "<br>--응답 데이터<br>name: 이름(실명?닉네임?)<br>id:아이디<br>number:사업자등록번호" +
            "<br>state:상태(잠금여부 0:정상 1:잠금)<br>(실패)<br>(500)INTERNAL_SERVER_ERROR<br>")
    public List<BlackVo> blackList(){return service.blackList1();}
    /*7.계정 정지/정지 해제(토글로 처리)
    1)이거 실행하기 전에 경고 문구 같은 거 있는 컨펌 창 하나 띄워주는 건 어떤가요*/
    @PatchMapping
    @Operation(summary="계정 잠금",description="계정 잠금/해제(토글로 처리)" +
            "<br>--요구 데이터<br>iuser:유저 pk<br>--응답 데이터<br>(성공)<br>result: 1(잠금) or 0(해제)" +
            "<br>(실패)<br>(500)INTERNAL_SERVER_ERROR<br>")
    public ResVo suspendAccount(int iuser){
        if(iuser<=0){throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);}
        return service.suspendAccount2(iuser);
    }
    //잠금 여부 확인 후 토글로 처리(select result->update)


}
