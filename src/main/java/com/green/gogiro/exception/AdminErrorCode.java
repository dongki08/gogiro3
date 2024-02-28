package com.green.gogiro.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AdminErrorCode implements ErrorCode{
    ALREADY_BLINDED(HttpStatus.BAD_REQUEST,"이미 숨김 처리된 글입니다"),
    NO_REPORTED(HttpStatus.BAD_REQUEST,"신고당한 글이 아닙니다"),
    INVALID_IUSER(HttpStatus.BAD_REQUEST,"유효하지 않은 유저PK입니다");
    private final HttpStatus httpStatus;
    private final String message;
}
