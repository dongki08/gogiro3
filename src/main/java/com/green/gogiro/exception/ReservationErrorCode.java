package com.green.gogiro.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReservationErrorCode implements ErrorCode{
    CANT_CANCEL(HttpStatus.UNAUTHORIZED,"취소할 수 없습니다");

    private final HttpStatus httpStatus;
    private final String message;

}
