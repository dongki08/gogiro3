package com.green.gogiro.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReservationErrorCode implements ErrorCode{
    CANT_CANCEL(HttpStatus.UNAUTHORIZED,"취소할 수 없습니다"),
    INVALID_RESERVATION(HttpStatus.NOT_FOUND, "존재하지 않는 예약입니다."),
    ALREADY_CANCELED(HttpStatus.BAD_REQUEST,"이미 취소된 예약입니다"),
    PASSED_BY_DATE(HttpStatus.BAD_REQUEST,"이미 지난 날짜입니다"),
    CANT_UPDATE(HttpStatus.BAD_REQUEST,"예약 혹은 픽업을 당일에 변경할 수 없습니다"),
    INVALID_SHOP(HttpStatus.BAD_REQUEST,"후기를 작성하실 수 없는 가게입니다"),
    INVALID_DATE(HttpStatus.BAD_REQUEST,"아직 예약하실 수 없는 날짜입니다"),
    INVALID_PAYMENT(HttpStatus.BAD_REQUEST,"결제 금액이 올바르지 않습니다");
    private final HttpStatus httpStatus;
    private final String message;

}
