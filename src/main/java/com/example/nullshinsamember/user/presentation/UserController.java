package com.example.nullshinsamember.user.presentation;

import com.example.nullshinsamember.common.constant.UserConst;
import com.example.nullshinsamember.user.application.UserService;
import com.example.nullshinsamember.user.domain.dto.request.UserSignInRequest;
import com.example.nullshinsamember.user.domain.dto.request.UserSignUpRequest;
import com.example.nullshinsamember.user.domain.dto.response.UserSignInResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody UserSignUpRequest request){
        userService.signUp(request.getEmail(), request.getPassword(), request.getName());
    }

    @PostMapping("/sign-in")
    public UserSignInResponse signIn(@Valid @RequestBody UserSignInRequest request, HttpSession httpSession){
        String sessionId = userService.signIn(request.getEmail(), request.getPassword());
        httpSession.setAttribute(UserConst.USER_SESSION_ID_KEY, sessionId);
        return new UserSignInResponse(httpSession.getId());
    }
}
