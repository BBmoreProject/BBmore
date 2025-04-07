package com.bbmore.product.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImgDTO {

    private Integer productImgCode;           // 상품이미지고유번호

    private LocalDate regTime;            // 이미지 등록일

    private LocalDate updateTime;           //  이미지 수정일

    private String createdBy;        // 이미지 생성자(사람)

    private String productImgName;        // 상품 이미지이름

    private String productImgOriName;        // 상품 이미지원본이름

    private String productImgRepYn;        // 상품 대표이미지

    private String productImgUrl;            // 상품 이미지경로


    private Integer productCode;            // fk 상품고유번호
}
