package com.bbmore.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDTO {

    /**
     * 엔티티(Entity) - 데이터베이스 테이블과의 매핑에 집중
     *
     * 데이터베이스 테이블 구조를 정확히 반영
     * JPA 어노테이션을 통한 컬럼 매핑, 관계 설정
     * 영속성 컨텍스트에서 관리되는 객체
     *
     *
     * DTO(Data Transfer Object) - API와 서비스 계층의 데이터 전송에 집중
     *
     * 클라이언트에 필요한 데이터만 포함
     * 표현 계층에 최적화된 구조
     * 비즈니스 로직이나 검증 로직을 포함할 수 있음
     */

    private Long id;

    private String productName;

    private Integer price;

    private String productDetail;

    private String sellStatus;

    ///  LocalDateTime 클래스는 Java 8부터 도입된 날짜와 시간을 함께 다루는 클래스
    private LocalDateTime regTime; // registration time 등록 날짜

    private LocalDateTime updateTime;


}
