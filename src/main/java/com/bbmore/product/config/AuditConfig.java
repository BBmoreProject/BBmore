package com.bbmore.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing /// Spring Data JPA의 감사(Auditing) 기능을 위한 인터페이스를 반환합니다.
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    /**
     * return new AuditorAwareImpl():
     * AuditorAwareImpl 클래스의 새 인스턴스를 생성하여 반환합니다.
     * 이 클래스는 앞서 본 AuditorAware<String> 인터페이스의 구현체입니다.
     */

}
