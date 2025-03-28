package com.bbmore.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
/**
 * @Configuration이 있으면 하나 이상의 @Bean을 포함할 수 있고 스프링 컨테이너에 의해 Bean 정의
 * XML 파일 대체
 * Bean 의존성 관리
 * 같은 @Configuration 클래스 내에서 다른 @Bean 메소드를 호출할 때 싱글톤 보장을 제공
 * @Configuration은 내부적으로 @Component 포함하고 있어 컴포넌트 스캔 대상
 *
 * @Bean: 스프링에게 이 메서드가 반환하는 객체를 만들어서 보관해
 * 이 메서드를 호출할 때마다 새로운 객체를 생성하지 않고 보관된 객체를 반환(스프링 컨테이너라는 큰 상자 안에 보관)
 * 빈은 메서드 이름을 ID로 사용해서 구분해(이름표처럼)
 *
 * @Configuration: 스프링에게 이 클래스는 설정 정보를 담고 있어요
 * 1. 부품(Bean)의 설계도와 같음
 * 2. @Bean이 붙은 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록
 * 3. 같은 빈이 여러번 생성되지 않도록 함(싱글톤 디자인 패턴)
 *
 */
@EnableWebSecurity
public class SecurityConfig{

}
