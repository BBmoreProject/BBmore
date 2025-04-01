package com.bbmore.member.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_code")
  private Integer productCode;  // 상품 고유 번호 (Primary Key)

  @Column(name = "product_name")
  private String productName;  // 상품 이름

  @Column(name = "product_price")
  private Integer productPrice;  // 상품 가격

  @Column(name = "product_quantity")
  private Integer productQuantity;  // 상품 재고




}
