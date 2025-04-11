package com.bbmore.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Table(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_code")
    private Integer productCode;  // 상품고유번호

    @Column(name = "product_name", nullable = false)
    private String productName;             // 상품이름

    @Column(name = "product_price", nullable = false)
    private Integer productPrice;           // 상품금액

//    @Column(name = "product_img", nullable = false)
//    private String productImg;  // 이미지 경로

    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity;        // 상품재고


    // fk(카테고리고유번호)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_code")
    private Category category;

}
