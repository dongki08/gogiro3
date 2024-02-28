package com.green.gogiro.reservation;


import com.green.gogiro.common.Const;
import com.green.gogiro.common.ResVo;
import com.green.gogiro.exception.AuthErrorCode;
import com.green.gogiro.exception.RestApiException;
import com.green.gogiro.reservation.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static com.green.gogiro.exception.ReservationErrorCode.*;
import static java.lang.Math.abs;


@Tag(name = "예약(픽업)",description = "예약(픽업) API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReservationController {
    private final ReservationService service;

    @PostMapping("/reservation")
    @Operation(summary = "예약 등록",description = "예약 등록 처리<br>--요청 데이터<br>ishop(NOT NULL, 최소 1 이상): 고기집pk" +
            "<br>date(NOT NULL): 예약날짜(ex)1999-12-01 10:00:00<br>request: 요청 사항" +
            "<br>headCount(최소 1 이상): 인원수<br>--응답 데이터<br>(성공)<br>pk: 예약pk" +
            "<br>amount: 예약금<br>(실패)<br>(400)NOT_DATE(0000-00-00 00:00:00)<br>PASSED_BY_DATE(이미 지난 날짜입니다)" +
            "<br>INVALID_PARAMETER(날짜 형식이 올바르지 않습니다)<br>INVALID_DATE(아직 예약하실 수 없는 날짜입니다)" +
            "<br>(404)VALID_SHOP(DB에 없는 고기집)<br>(500)<br>INTERNAL_SERVER_ERROR")
    public ReservationVo postReservation(@RequestBody @Valid ReservationInsDto dto){
        if(dto.getLocalDateTime().isBefore(LocalDateTime.now())){
            throw new RestApiException(PASSED_BY_DATE);
        } else if(dto.isInvalidDate()){
            throw new RestApiException(INVALID_DATE);
        }
        return service.postReservation2(dto);
    }

    @PostMapping("/pickup")
    @Operation(summary = "픽업 등록",description = "픽업 등록 처리<br>--요청 데이터<br>ibutcher(NOT NULL, 최소 1 이상): 정육점pk"+
            "<br>date(NOT NULL): 예약날짜(ex)1999-12-01 10:00:00<br>request: 요청 사항" +
            "<br>headCount(최소 1 이상): 인원수<br>menus<br>-ibutMenu(메뉴pk)<br>-count(메뉴 수량)<br>--응답 데이터<br>(성공)<br>pk: 픽업pk" +
            "<br>amount: 예약금<br>(실패)<br>(400)NOT_DATE(0000-00-00 00:00:00)<br>PASSED_BY_DATE(이미 지난 날짜입니다)" +
            "<br>INVALID_PARAMETER(날짜 형식이 올바르지 않습니다)<br>INVALID_DATE(아직 예약하실 수 없는 날짜입니다)" +
            "<br>REGEXP_DATE_TYPE(날짜를 올바르게 입력해주세요)" +
            "<br>INVALID_MENU_OR_COUNT(메뉴 혹은 수량이 잘못되었습니다)" +
            "<br>INVALID_PARAMETER<br>(날짜 형식이 올바르지 않습니다)<br>(메뉴를 입력해주세요)" +
            "<br>(500)<br>INTERNAL_SERVER_ERROR")
    public ReservationVo postPickup(@RequestBody @Valid PickupInsDto dto){
        if(dto.getLocalDateTime().isBefore(LocalDateTime.now())){
            throw new RestApiException(PASSED_BY_DATE);
        } else if(dto.isInvalidDate()){
            throw new RestApiException(INVALID_DATE);
        }
        return service.postPickup2(dto);
    }

    @PatchMapping("/reservation")
    @Operation(summary = "예약 취소",description = "예약 취소 처리<br>--요청 데이터<br>checkShop:가게 구분(0:고기집/1:정육점)" +
            "<br>ireser(최소 1 이상): 예약 혹은 픽업 pk<br>--응답 데이터<br>(성공)<br>result: 1" +
            "<br>(실패)<br>(400)<br>INVALID_PARAMETER<br>(가게구분 값이 잘못되었습니다)<br>(예약pk가 없습니다)" +
            "<br>INVALID_RESERVATION(존재하지 않는 예약입니다)<br>(500)<br>INTERNAL_SERVER_ERROR(서버에서 에러가 발생하였습니다)")
    public ResVo cancelReservation(@RequestBody  CancelDto dto){
        return service.cancelReservation2(dto);
    }

    @PutMapping("/reservation")
    @Operation(summary = "예약 변경",description = "예약 변경 처리<br>--요청 데이터<br>checkShop:가게 구분(0:고기집/1:정육점)"+
            "<br>ireser(최소 1 이상): 예약 혹은 픽업 pk<br>date:예약날짜(ex)1999-12-01 10:00:00" +
            "<br>request:요청 사항<br>headCount(최소 1 이상):인원 수<br>--응답 데이터<br>(성공)<br>result: 1" +
            "<br>(실패)<br>(400)<br>NOT_DATE(예약날짜를 입력해 주세요)<br>INVALID_PARAMETER<br>(날짜 형식이 올바르지 않습니다)<br>(예약날짜를 입력해 주세요)" +
            "<br>(인원 수를 입력해주세요)<br>(가게구분 값이 잘못되었습니다)<br>(500)<br>INTERNAL_SERVER_ERROR")
    public ResVo putReservation(@RequestBody @Valid ReservationUpdDto dto) {
        return service.putReservation2(dto);
    }

    @PostMapping(value="/review",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "후기 등록",description = "후기 등록 처리<br>--요청 데이터<br>pics:후기 사진(1~5장)<br>" +
            "<br>checkShop:가게구분(고기집 0,정육점 1)<br>ireser(최소 1 이상):예약 혹은 픽업 pk<br>ishop(최소 1 이상):가게pk" +
            "<br>star(1~5점):별점<br>review(1~50자):리뷰 내용<br>--응답 데이터<br>(성공)<br>ireview:리뷰pk" +
            "<br>pics(List-String):사진들의 파일명<br>(실패)<br>(400)<br>INVALID_PARAMETER<br>" +
            "(가게구분 값이 잘못되었습니다)<br>(예약 혹은 픽업 pk를 보내주세요)<br>(고기집 혹은 정육점 pk를 보내주세요)" +
            "<br>(별점은 최소 1점 입니다)<br>(별점은 최대 5점 입니다)<br>(댓글 내용은 1~50글자 사이를 만족해야 합니다)" +
            "<br>(400)<br>SIZE_PHOTO(사진이 5장 초과 입니다)<br>MUST_PHOTO(사진을 1장 이상 넣어 주세요)<br>INVALID_RESERVATION(존재하지 않는 예약입니다)" +
            "<br>(500)<br>INTERNAL_SERVER_ERROR")
    public ReviewPicsInsVo postReview(@Valid @RequestPart(required = false) List<MultipartFile> pics, @Valid @RequestPart ReviewDto dto){
        if(pics == null || pics.isEmpty()){
            throw new RestApiException(AuthErrorCode.MUST_PHOTO);
        } if(pics.size() > Const.PIC_MAX){
            throw new RestApiException(AuthErrorCode.SIZE_PHOTO);
        }
        dto.setFiles(pics);
        return service.postReview2(dto);
    }

    @PatchMapping("/reservation/confirm")
    @Operation(summary="예약 확정",description="가게 주인의 예약 및 픽업 확정" +
            "<br>--요구 데이터<br>checkShop:가게 구분(0:고기집, 1: 정육점)" +
            "<br>ireser:예약 혹은 픽업 pk<br>--응답 데이터<br>(성공)result:1" +
            "<br>(실패)<br>(400)<br>ALREADY_CANCELED / INVALID_RESERVATION / INVALID_PARAMETER" +
            "<br>(500)<br>INTERNAL_SERVER_ERROR")
    public ResVo confirmReservation(ConfirmDto dto){
        return service.confirmReservation(dto);
    }

    @PostMapping("/confirm")
    @Operation(summary="결제 승인",description="결제 승인 처리<br>" +
            "--요청 데이터<br>checkShop: 가게 구분(0: 고기집, 1: 정육점), ireser: 예약pk" +
            "<br>amount: 최종 결제 금액, <br>--응답 데이터<br>(성공)<br>checkShop: 가게 구분(0: 고기집, 1: 정육점), ireser: 예약pk" +
            "<br>amount: 최종 결제 금액<br>(실패)<br>(400)<br>INVALID_RESERVATION / INVALID_PAYMENT / INVALID_PARAMETER" +
            "<br>(500)<br>INTERNAL_SERVER_ERROR")
    public PaymentDto confirmPayment(@Valid @RequestBody PaymentDto dto) throws Exception {
        if(service.confirmPayment(dto)){throw new RestApiException(INVALID_PAYMENT);}
        String widgetSecretKey = "test_ck_6bJXmgo28eD0pPL4knJXrLAnGKWx";
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes("UTF-8"));
        String authorizations = "Basic " + new String(encodedBytes);
        URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(dto.toString().getBytes("UTF-8"));
        return dto;
    }
}
