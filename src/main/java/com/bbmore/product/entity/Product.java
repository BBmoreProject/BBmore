package com.bbmore.product.entity;

import com.bbmore.product.config.BaseEntity;
import com.bbmore.product.constant.ProductSellStatus;
import com.bbmore.product.dto.ProductFormDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "tbl_product")
public class Product extends BaseEntity {

    @Id
    ///  기본키 생성 전략 AUTO -> IDENTITY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "product_code")
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

    public void updateProduct(ProductFormDTO productFormDTO){
        this.productName = productFormDTO.getProductName();
        this.productPrice = productFormDTO.getPrice();
        this.productStock = productFormDTO.getStockNumber();
        this.productDetail = productFormDTO.getProductDetail();
        this.productSellStatus = productFormDTO.getProductSellStatus();
    }






}
