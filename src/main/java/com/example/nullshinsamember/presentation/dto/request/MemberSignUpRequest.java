package com.example.nullshinsamember.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberSignUpRequest(
        @NotBlank(message = "회원가입시 아이디는 필수 값 입니다.") String email,
        @NotBlank(message = "회원가입시 비밀번호는 필수 값 입니다.") String password,
        @NotBlank(message = "회원가입시 이름은 필수 값 입니다.") String name
) {
}
