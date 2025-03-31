package com.bbmore.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer productCode;  // 상품 고유 번호 (Primary Key)

  private String productName;  // 상품 이름
  private int productPrice;  // 상품 가격
  private int productQuantity;  // 상품 재고



}
