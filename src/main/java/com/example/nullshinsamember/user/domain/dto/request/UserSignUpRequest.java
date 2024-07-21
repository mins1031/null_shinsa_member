package com.example.nullshinsamember.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSignUpRequest {
    @NotBlank(message = "회원가입시 아이디는 필수 값 입니다.")
    private String email;
    @NotBlank(message = "회원가입시 비밀번호는 필수 값 입니다.")
    private String password;
    @NotBlank(message = "회원가입시 이름은 필수 값 입니다.")
    private String name;

    public UserSignUpRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
