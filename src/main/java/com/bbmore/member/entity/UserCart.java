package com.bbmore.member.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.lang.reflect.Member;


@Getter
@Entity
@Table(name = "tbl_user_cart" )
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

  @ManyToOne
  @JoinColumn(name = "user_code", nullable = false)
  private Member member; // FK 회원

  @ManyToOne
  @JoinColumn(name = "product_code", nullable = false)
  private Product product; // FK 상품

  // 생성자에서 모든 필드 초기화
  public UserCart(int cartProductQuantity, Member member, Product product) {
    this.cartProductQuantity = cartProductQuantity;
    this.member = member;
    this.product = product;
  }

  public void setCartCode(Integer cartCode) {
    this.cartCode = cartCode;
  }

  public void setCartProductQuantity(Integer cartProductQuantity) {
    this.cartProductQuantity = cartProductQuantity;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
