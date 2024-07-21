package com.example.nullshinsamember.presentation.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSignInResponse {
    private String sessionId;

    public UserSignInResponse(String sessionId) {
        this.sessionId = sessionId;
    }
}
