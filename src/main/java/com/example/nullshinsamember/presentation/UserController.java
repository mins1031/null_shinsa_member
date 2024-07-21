package com.example.nullshinsamember.presentation;

import com.example.nullshinsamember.application.UserService;
import com.example.nullshinsamember.common.constant.UserConst;
import com.example.nullshinsamember.presentation.dto.request.UserSignInRequest;
import com.example.nullshinsamember.presentation.dto.request.UserSignUpRequest;
import com.example.nullshinsamember.presentation.dto.response.UserSignInResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/hello")
    public String test(){
        return "Hello";
    }
}
