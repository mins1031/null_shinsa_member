package com.example.nullshinsamember.application;

import com.example.nullshinsamember.infrastructure.repository.UserRepository;
import com.example.nullshinsamember.user.domain.entity.User;
import com.example.nullshinsamember.utils.UserUtils;
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
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void 회원가입() {
        //given
        String email = "user@email.com";
        String pw = "user_pw";
        String name = "user_name";

        User user = User.builder()
                .email(email)
                .password(pw)
                .name(name)
                .build();

        //when
        when(userRepository.save(any())).thenReturn(user);

        userService.signUp(email, name, pw);

        //then
        verify(userRepository, atLeastOnce()).save(any());
    }

    @Test
    void 로그인() {
        //given
        String email = "user@email.com";
        String pw = "user_pw";
        String name = "user_name";

        User user = User.builder()
                .email(email)
                .password(pw)
                .name(name)
                .build();

        //when
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        String sessionId = userService.signIn(email, user.getPassword());

        //then
        assertEquals(UserUtils.generateUserSessionValue(email, pw), sessionId);
    }
}