package com.example.nullshinsamember.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MemberExceptionCode {
    NOT_EXIST_MEMBER_EMAIL(HttpStatus.BAD_REQUEST, 1000, "회원이 아닌 이메일 정보입니다. 이메일 정보를 확인해주세요."),
    WRONG_LOGIN_PASSWORD(HttpStatus.BAD_REQUEST, 1001, "회원 이메일과 비밀번호가 일치하지 않습니다. 패스워드 정보를 확인해주세요."),

    ;

    private final HttpStatus httpStatus;
    private final int errorCode;
    private final String errorMessage;

    MemberExceptionCode(HttpStatus httpStatus, int errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
