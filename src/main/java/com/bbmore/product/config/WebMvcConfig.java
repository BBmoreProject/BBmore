package com.bbmore.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.Serializable;

public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * WebMvcConfigurer 인터페이스 구현:
     *
     * Spring MVC의 기본 설정을 확장할 수 있게 해주는 인터페이스를 구현합니다.
     * 이 인터페이스의 메서드들을 오버라이드하여 웹 MVC 설정을 커스터마이즈할 수 있습니다.
     *
     *
     * @Value 어노테이션:
     *
     * @Value("${uploadPath}") 어노테이션을 사용하여 application.yml 파일에서 정의한 uploadPath 속성 값을 주입받습니다.
     * 이 값은 이미지 파일이 저장될 실제 시스템 경로를 지정합니다.
     *
     *
     * addResourceHandlers 메서드 오버라이드:
     *
     * 정적 리소스의 위치를 매핑하기 위한 메서드를 구현합니다.
     * /images/** URL 패턴으로 들어오는 모든 요청을 uploadPath에 지정된 실제 파일 시스템 경로로 연결합니다.
     */

    @Value("${uploadPath}") ///  yml 파일 읽어옴
    String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);
    }


}
