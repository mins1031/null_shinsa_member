package com.example.nullshinsamember.application;

import com.example.nullshinsamember.domain.entity.Member;
import com.example.nullshinsamember.domain.enumeration.MemberRole;
import com.example.nullshinsamember.exception.MemberException;
import com.example.nullshinsamember.exception.MemberExceptionCode;
import com.example.nullshinsamember.infrastructure.repository.MemberRepository;
import com.example.nullshinsamember.utils.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RedisTemplate<String, String> redisTemplate;

    //TODO 이메일 인증 Or 문자인증 등등 인증작업 및 임시회원 상태가 필요하지만.. 우선 간단하게 구현합니다.
    @Transactional
    public void signUp(final String email, final String password, final String name) {
        final Member signUpMember = Member.of(email, password, name, false, MemberRole.NORMAL_CONSUMER);
        memberRepository.save(signUpMember);
    }

    @Transactional(readOnly = true)
    public String signIn(final String email, final String password) {
        Member memberByEmail = memberRepository.findByEmail(email).orElseThrow(() -> new MemberException(MemberExceptionCode.NOT_EXIST_MEMBER_EMAIL));
        if (!memberByEmail.isMatchPassword(password)) {
            throw new MemberException(MemberExceptionCode.WRONG_LOGIN_PASSWORD);
        }

        return MemberUtils.generateMemberSessionValue(memberByEmail.getEmail(), memberByEmail.getPassword());
    }
}
