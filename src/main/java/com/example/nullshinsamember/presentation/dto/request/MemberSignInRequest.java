package com.example.nullshinsamember.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberSignInRequest(
        @NotBlank(message = "로그인시 이메일은 필수 값 입니다.") String email,
        @NotBlank(message = "로그인시 비밀번호는 필수 값 입니다.") String password
) {
}
