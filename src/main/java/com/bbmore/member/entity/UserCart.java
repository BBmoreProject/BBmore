package com.bbmore.member.entity;

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

  @OneToOne // 회원들은 각자 자신의 장바구니 갖고있고(일대일), 장바구니 입장에서도 자신과 매핑되는 1명의 회원을 가짐(일대일)
  @JoinColumn(name = "user_code", nullable = false)
  private Member member; // FK 회원

  @ManyToOne    // 하나의 장바구니에는 여러 개의 상품이 들어갈 수 있음
  @JoinColumn(name = "product_code", nullable = false)
  private Product product; // FK 상품

  // 생성자에서 모든 필드 초기화
  public UserCart(int cartProductQuantity, Member member, Product product) {
    this.cartProductQuantity = cartProductQuantity;
    this.member = member;
    this.product = product;
  }

  // 수량을 설정하는 setter 메서드
  public void setCartProductQuantity(Integer cartProductQuantity) {
    if (cartProductQuantity < 0) {
      throw new IllegalArgumentException("수량은 0보다 작을 수 없습니다.");
    }
    this.cartProductQuantity = cartProductQuantity;
  }

  // 수량을 가져오는 getter 메서드
  public Integer getCartProductQuantity() {
    return this.cartProductQuantity;
  }


}
