package com.bbmore.product.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * 엔티티의 수정 삭제 기록하는 클래스
 * AuditorAwareImpl 클래스는 Spring Data JPA의 AuditorAware<T> 인터페이스를 구현합니다.
 * 이 인터페이스는 엔티티의 생성자/수정자를 자동으로 기록하는 데 사용됩니다.
 *
 *
 * AuditorAware<String>에서 제네릭 타입 String은 감사 정보(생성자/수정자)가 문자열 형태로 저장될 것임을 의미합니다.
 * getCurrentAuditor() 메소드는 현재 감사자(작업을 수행하는 사용자)의 정보를 제공하기 위해 오버라이드됩니다.
 *
 * SecurityContextHolder.getContext().getAuthentication()는 Spring Security의 현재 인증 정보를 가져옵니다:
 *
 * SecurityContextHolder는 Spring Security에서 현재 실행 스레드의 보안 컨텍스트를 저장하는 클래스입니다.
 * getContext()는 현재 스레드의 보안 컨텍스트를 반환합니다.
 * getAuthentication()은 현재 인증된 사용자의 인증 객체를 반환합니다.
 */

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String userId = "";
        if (authentication != null) {
            userId = authentication.getName();
        }
        return Optional.of(userId);
    }
}
