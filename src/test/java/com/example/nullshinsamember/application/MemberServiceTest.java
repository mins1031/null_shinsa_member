package com.example.nullshinsamember.application;

import com.example.nullshinsamember.domain.entity.Member;
import com.example.nullshinsamember.domain.enumeration.MemberRole;
import com.example.nullshinsamember.infrastructure.repository.MemberRepository;
import com.example.nullshinsamember.utils.MemberUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void 회원가입() {
        //given
        String email = "user@email.com";
        String pw = "user_pw";
        String name = "user_name";

        Member member = Member.of(email, pw, name, false, MemberRole.NORMAL_CONSUMER);

        //when
        when(memberRepository.save(any())).thenReturn(member);

        memberService.signUp(email, name, pw);

        //then
        verify(memberRepository, atLeastOnce()).save(any());
    }

    @Test
    void 로그인() {
        //given
        String email = "user@email.com";
        String pw = "user_pw";
        String name = "user_name";

        Member member = Member.of(email, pw, name, false, MemberRole.NORMAL_CONSUMER);

        //when
        when(memberRepository.findByEmail(email)).thenReturn(Optional.of(member));

        String sessionId = memberService.signIn(email, member.getPassword());

        //then
        assertEquals(MemberUtils.generateMemberSessionValue(email, pw), sessionId);
    }
}