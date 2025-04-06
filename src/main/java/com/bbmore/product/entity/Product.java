package com.bbmore.product.entity;

import com.bbmore.product.config.BaseEntity;
import com.bbmore.product.constant.ProductSellStatus;
import com.bbmore.product.dto.ProductFormDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "tbl_product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_code")
    private Integer productCode;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private Integer productPrice;

    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity;    // 상품 재고

    @Column(name = "product_detail")
    private String productDetail;

    @Column(name = "product_sell_status")
    private ProductSellStatus productSellStatus;

    /// 도메인 생성주기 관리

    public void updateProduct(ProductFormDto productFormDto) {
        this.productName = productFormDto.getProductName();
        this.productPrice = productFormDto.getProductPrice();
        this.productQuantity = productFormDto.getProductQuantity();
        this.productDetail = productFormDto.getProductDetail();
        this.productSellStatus = productFormDto.getProductSellStatus();
    }
}
