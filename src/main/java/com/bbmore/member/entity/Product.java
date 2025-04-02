package com.bbmore.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_code")
    private Integer productCode;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private Integer productPrice;

    @Column(name = "product_img", nullable = false)
    private String productImg;  // 이미지 경로

    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity;    // 상품 재고

    // FK
//    @ManyToOne
//    @JoinColumn(name = "category_code", nullable = false)
//    private Category category;

    @ManyToOne(fetch = FetchType.LAZY) // 수정 필요 (?)
    @JoinColumn(name = "order_detail_code", nullable = false)
    private OrderDetail orderDetail;
}
