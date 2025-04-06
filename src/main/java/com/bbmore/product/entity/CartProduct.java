package com.bbmore.product.entity;

import com.bbmore.product.config.BaseEntity;
import com.bbmore.product.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name= "cart_product")
public class CartProduct extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "cart_product_code")
    private Integer cartProductCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_code")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code")
    private Product product;

    private int count;
}
