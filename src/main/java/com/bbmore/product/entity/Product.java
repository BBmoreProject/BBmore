package com.bbmore.product.entity;

import com.bbmore.product.constant.ProductSellStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "tbl_product")
public class Product {

    @Id
    ///  기본키 생성 전략 AUTO -> IDENTITY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_code")
    private Long productId;

    /// 항상 값이 있어야 하는 필드는 Not Null 설정
    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private int productPrice;

    @Column(name = "product_quantity", nullable = false)
    private int productStock;

    private String productDetail;

    @Enumerated(EnumType.STRING)
    private ProductSellStatus productSellStatus;

    ///  생성시간, 수정시간
    private LocalDateTime productCreateTime;

    private LocalDateTime productUpdateTime;





}
