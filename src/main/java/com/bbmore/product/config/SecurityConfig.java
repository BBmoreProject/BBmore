package com.bbmore.product.config;


import com.bbmore.product.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 관리자 페이지는 관리자만 볼 수 있게 합니다.
     * 회원 페이지는 로그인한 사용자만 볼 수 있게 합니다.
     * 그 외 페이지는 모든 사람이 볼 수 있게 합니다.
     * 로그인과 로그아웃 방법을 정의합니다.
     * 비밀번호는 안전하게 암호화해서 저장합니다.
     */

    private final MemberService memberService;

    public SecurityConfig(MemberService memberService) {
        this.memberService = memberService;
    }


    /**
     * 무한 리디렉션이 발생한 이유는 로그인 페이지 자체에 접근하려면 인증이 필요한데,
     * 인증되지 않으면 다시 로그인 페이지로 리디렉션되는 순환 구조 때문이었습니다. 이제 로그인 관련 페이지는 별도로 접근 허용하여 이 문제를 해결했습니다.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        authorizeHttpRequestsCustomizer ->
                                authorizeHttpRequestsCustomizer
                                        /**
                                         * 예전에는 정적 리소스(/css/, /js/, /img/** 등)에 대한 보안 필터 적용을 제외하기 위해
                                         * WebSecurity의 ignoring() 메서드를 사용했습니다. 하지만 현재 코드에서는
                                         * 이미 다음과 같이 HttpSecurity의
                                         * authorizeHttpRequests() 내에서 정적 리소스에 대한 접근을 허용하고 있습니다:
                                         */
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                        .requestMatchers("/", "/members/**", "/product/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/members/login")
                                .usernameParameter("email")
                                .defaultSuccessUrl("/")
                                .failureUrl("/members/login/error")
                )
                .logout(logout ->
                        logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)      // HTTP 세션 무효화를 추가했습니다
                )
                .exceptionHandling(e -> e
                    .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        )
                // 나머지 설정 유지
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); /// BCrypt 알고리즘을 사용해 비밀번호를 해시화
    }

    @Bean ///  보안 메서드
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        ///

        authenticationManagerBuilder
                .userDetailsService(memberService) ///  사용자 정보 찾기
                .passwordEncoder(passwordEncoder()); /// 비밀번호 확인

        return authenticationManagerBuilder.build();
        /**
         * build()는 지금까지 설정한 모든 보안 요구사항을 바탕으로
         * 실제로 작동하는 인증 관리자(AuthenticationManager)를 생성
         *
         *  이 코드는 "모든 설정을 마쳤으니, 이제 실제로 작동하는 인증 시스템을 만들어서
         *  Spring에게 전달해줘"라는 의미입니다. 이 인증 시스템은 이후 사용자가 로그인할 때마다 사용
         */
    }




}