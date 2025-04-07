package com.bbmore.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Table(name = "tbl_product_img")
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_img_code")
    private Integer productImgCode;        // 상품이미지고유번호

    @Column(name = "reg_time")
    private LocalDate regTime;             // 이미지 등록일

    @Column(name = "update_time")
    private LocalDate updateTime;           // 이미지 수정일

    @Column(name = "created_by")
    private String createdBy;               // 이미지 생성자

    @Column(name = "product_img_name")
    private String productImgName;          // 상품 이미지이름

    @Column(name = "product_img_ori_name")
    private String productImgOriName;        // 상품 이미지 원본이름

    @Column(name = "product_img_rep_yn")
    private String productImgRepYn;        // 상품 대표이미지

    @Column(name = "product_img_url")
    private String productImgUrl;        // 상품 이미지경로


    // fk(상품고유번호)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code")
    private Product product;

}
