package com.bbmore.product.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration ///  설정 클래스
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadPath}")
    String uploadPath; /// yml 에 설정한 프로퍼티 값을 읽어옴

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**") ///  images 로 시작하는 모든 URL이 핸들러 대상이 됨
                .addResourceLocations(uploadPath); /// 실제 파일 시스템에서 리소스 찾을 위치를 설정함

        /**
         * url 이  images 시작하는 경로 uploadPath 설정한 폴더를 기준으로 파일을 읽어오도록 설정
         * 로컬 컴퓨터에 지정된 파일을 읽어올 root 경로 설정
         *
         */
    }
}
