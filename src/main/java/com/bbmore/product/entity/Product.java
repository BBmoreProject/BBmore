package com.bbmore.product.entity;

import com.bbmore.product.config.BaseEntity;
import com.bbmore.product.constant.ProductSellStatus;
import com.bbmore.product.dto.ProductFormDTO;
import com.bbmore.product.exception.OutOfStockException;
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
    private Long id;

    /// 항상 값이 있어야 하는 필드는 Not Null 설정
    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private int price;

    @Column(name = "product_quantity", nullable = false)
    private int stockNumber;

    private String productDetail;

    @Enumerated(EnumType.STRING)
    private ProductSellStatus productSellStatus;

    public void updateProduct(ProductFormDTO productFormDTO){
        this.productName = productFormDTO.getProductName();
        this.price = productFormDTO.getPrice();
        this.stockNumber = productFormDTO.getStockNumber();
        this.productDetail = productFormDTO.getProductDetail();
        this.productSellStatus = productFormDTO.getProductSellStatus();
    }

    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;
        if (restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다." +
                    "(현재 재고 수량: " + this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }

    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }
}
