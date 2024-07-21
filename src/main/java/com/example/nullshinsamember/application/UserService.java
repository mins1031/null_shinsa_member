package com.example.nullshinsamember.user.application;

import com.example.nullshinsamember.user.domain.entity.User;
import com.example.nullshinsamember.user.exception.UserException;
import com.example.nullshinsamember.user.exception.UserExceptionCode;
import com.example.nullshinsamember.user.infrastructure.repository.UserRepository;
import com.example.nullshinsamember.user.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RedisTemplate<String, String> redisTemplate;

    //TODO 이메일 인증 Or 문자인증 등등 인증작업 및 임시회원 상태가 필요하지만.. 우선 간단하게 구현합니다.
    @Transactional
    public void signUp(final String email, final String password, final String name) {
        final User signUpUser = User.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
        userRepository.save(signUpUser);
    }

    @Transactional(readOnly = true)
    public String signIn(final String email, final String password) {
        User userByEmail = userRepository.findByEmail(email).orElseThrow(() -> new UserException(UserExceptionCode.NOT_EXIST_USER_EMAIL));
        if (!userByEmail.isMatchPassword(password)) {
            throw new UserException(UserExceptionCode.WRONG_LOGIN_PASSWORD);
        }

        return UserUtils.generateUserSessionValue(userByEmail.getEmail(), userByEmail.getPassword());
    }
}
