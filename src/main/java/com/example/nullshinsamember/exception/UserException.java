package com.example.nullshinsamember.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException {
    private UserExceptionCode userExceptionCode;

    public UserException(UserExceptionCode userExceptionCode) {
        super(userExceptionCode.getErrorMessage());
        this.userExceptionCode = userExceptionCode;
    }
}
