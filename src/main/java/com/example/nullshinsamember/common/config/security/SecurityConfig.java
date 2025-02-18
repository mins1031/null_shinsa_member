package com.example.nullshinsamember.common.config.security;

import com.example.nullshinsamember.application.SecurityUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUnAuthorizationFilter unauthorizedEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final SecurityUserDetailService securityUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//                csrf((csrfConfig) ->
//                        csrfConfig.disable()
//                )
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers(
                                        "/hello",
                                        "/sign-up",
                                        "/sign-in"
                                )
                                .permitAll()
                )
                .formLogin((formLogin) ->
                        formLogin
//                                .loginPage() // 시큐리티 기본으로 설정된 /login 외에 커스텀한 로그인 페이지 url 설정
                                .usernameParameter("email") // 로그인시 id 파라미터 네이밍
                                .passwordParameter("password") // 로그인시 pwd 파라미터 네이밍
                                .loginProcessingUrl("/sign-in") // 로그인 api url
//                                .defaultSuccessUrl() // 로그인 성공시 리다이렉팅 될 url
                )
                .exceptionHandling((exceptionConfig) ->
                        exceptionConfig
                                .authenticationEntryPoint(unauthorizedEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler)
                )
                .userDetailsService(securityUserDetailService)
                ;

        return httpSecurity.build();
    }
}
