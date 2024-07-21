package com.example.nullshinsamember.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSignInRequest {
    @NotBlank(message = "로그인시 이메일은 필수 값 입니다.")
    private String email;
    @NotBlank(message = "로그인시 비밀번호는 필수 값 입니다.")
    private String password;

    public UserSignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
