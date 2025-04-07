package com.bbmore.member.entity;

import com.bbmore.product.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@Entity
@Table(name = "tbl_user_cart" )
@NoArgsConstructor   // 기본 생성자 자동 생성
public class UserCart {

  // 장바구니 하나에는 상품 종류는 하나만 가능(수량은 별개)

//
//  cart_code  INTEGER AUTO_INCREMENT COMMENT '장바구니 고유번호',
//  cart_product_quantity INTEGER NOT NULL COMMENT '장바구니상품수량',
//  CONSTRAINT pk_cart_code PRIMARY KEY (cart_code),
//  user_code INTEGER NOT NULL COMMENT '회원고유번호 (FK)',
//  product_code INTEGER NOT NULL COMMENT '상품고유번호 (FK)',

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cart_code")
  private Integer cartCode; // PK 장바구니 고유번호

  @Column(name = "cart_product_quantity", nullable = false)
  private Integer cartProductQuantity;  // 장바구니 상품수량


  @ManyToOne  // (고유번호에 AI 걸어놔서 예)고유번호: 1~3 이 user_code: 2번 => N:1 / 고유번호 1개당 회원 1명도 가능)
  @JoinColumn(name = "user_code", nullable = false)
  private Member member; // FK 회원


  @OneToOne  // 장바구니 고유번호 AI 걸어놔서 장바구니1:상품1)
  @JoinColumn(name = "product_code", nullable = false)
  private Product product; // FK 상품



}
