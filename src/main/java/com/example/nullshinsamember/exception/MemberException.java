package com.example.nullshinsamember.exception;

import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {
    private MemberExceptionCode memberExceptionCode;

    public MemberException(MemberExceptionCode memberExceptionCode) {
        super(memberExceptionCode.getErrorMessage());
        this.memberExceptionCode = memberExceptionCode;
    }
}
