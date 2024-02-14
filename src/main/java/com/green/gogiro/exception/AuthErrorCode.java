package com.green.gogiro.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum AuthErrorCode implements ErrorCode{

    INVALID_EXIST_USER_ID(HttpStatus.NOT_FOUND,"아이디가 존재하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.NOT_FOUND,"비밀번호를 확인해주세요."),
    NEED_SIGNIN(HttpStatus.UNAUTHORIZED,"로그인이 필요합니다."),
    INVALID_PAGE(HttpStatus.BAD_REQUEST,"잘못된 페이지가 입력되었습니다."),
    SIZE_PHOTO(HttpStatus.BAD_REQUEST,"사진이 5장 초과 입니다."),
    MUST_PHOTO(HttpStatus.BAD_REQUEST,"사진을 1장 이상 넣어 주세요."),
    INVALID_CATEGORY(HttpStatus.NOT_FOUND,"존재하지 않는 카테고리입니다."),
    NOT_STAR(HttpStatus.BAD_REQUEST,"별점을 선택해 주세요."),
    NOT_COMMUNITY_TITLE(HttpStatus.BAD_REQUEST,"제목을 입력해 주세요."),
    NOT_CONTENT(HttpStatus.BAD_REQUEST,"내용을 입력해 주세요."),
    VALID_SHOP(HttpStatus.NOT_FOUND,"존재하지 않는 식당(정육점)입니다."),
    VALID_BOARD(HttpStatus.NOT_FOUND,"존재하지 않는 게시판 입니다."),
    CHECK_SHOP(HttpStatus.BAD_REQUEST,"식당이 일치하지 않습니다."),
    NOT_COMMUNITY(HttpStatus.NOT_FOUND, "잘못된 등록입니다."),
    NOT_DATE(HttpStatus.BAD_REQUEST, "예약날짜를 입력해 주세요."),
    NOT_COMMUNITY_CHECK(HttpStatus.NOT_FOUND, "등록된 글을 찾을 수 없습니다."),
    NOT_COMMUNITY_ENTITY(HttpStatus.BAD_REQUEST, "다른 유저의 게시글입니다."),
    REGEXP_DATE_TYPE(HttpStatus.BAD_REQUEST,"날짜를 올바르게 입력해 주세요."),
    INVALID_MENU_OR_COUNT(HttpStatus.BAD_REQUEST,"메뉴 혹은 수량이 잘못되었습니다."),
    SEARCH_COMMUNITY(HttpStatus.NOT_FOUND, "해당하는 검색 결과가 없습니다."),
    INVALID_RESERVATION(HttpStatus.NOT_FOUND, "존재하지 않는 예약입니다."),
    REGEXP_PIC(HttpStatus.BAD_REQUEST,"이미지 파일이 아닙니다"),


    NOT_FOUND_REFRESH_TOKEN(HttpStatus.NOT_FOUND, "refresh-token이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    AuthErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
