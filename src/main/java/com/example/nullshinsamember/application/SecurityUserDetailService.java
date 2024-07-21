package com.example.nullshinsamember.application;

import com.example.nullshinsamember.domain.entity.Member;
import com.example.nullshinsamember.exception.MemberException;
import com.example.nullshinsamember.exception.MemberExceptionCode;
import com.example.nullshinsamember.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SecurityUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member memberEntity = memberRepository.findByEmail(email).orElseThrow(() -> new MemberException(MemberExceptionCode.NOT_EXIST_MEMBER_EMAIL));

        return User.builder()
                .username(memberEntity.getEmail())
                .roles(memberEntity.getRole().getRole())
                .build()
                ;
    }


}
