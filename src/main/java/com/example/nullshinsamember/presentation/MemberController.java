package com.example.nullshinsamember.presentation;

import com.example.nullshinsamember.application.MemberService;
import com.example.nullshinsamember.common.constant.MemberConst;
import com.example.nullshinsamember.presentation.dto.request.MemberSignInRequest;
import com.example.nullshinsamember.presentation.dto.request.MemberSignUpRequest;
import com.example.nullshinsamember.presentation.dto.response.MemberSignInResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 로그인
    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody MemberSignUpRequest request){
        memberService.signUp(request.getEmail(), request.getPassword(), request.getName());
    }

    // 회원가입
    @PostMapping("/sign-in")
    public MemberSignInResponse signIn(@Valid @RequestBody MemberSignInRequest request, HttpSession httpSession){
        String sessionId = memberService.signIn(request.getEmail(), request.getPassword());
        httpSession.setAttribute(MemberConst.MEMBER_SESSION_ID_KEY, sessionId);
        return new MemberSignInResponse(httpSession.getId());
    }

    @GetMapping("/hello")
    public String test(){
        return "Hello";
    }
}
