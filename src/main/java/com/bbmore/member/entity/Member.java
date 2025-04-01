package com.bbmore.member.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table(name = "tbl_member")
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_code")
  private Integer userCode;  // 회원 고유 번호 (Primary Key)

  // 장바구니와 1:1 관계
//  @OneToOne(mappedBy = "member")  // 이 관계는 `UserCart` 엔티티에 의해 관리됨
//  private UserCart userCart; // 회원이 가질 수 있는 장바구니

  @OneToMany(mappedBy = "member") // 회원은 여러 개의 장바구니 항목을 가질 수 있다.
  private List<UserCart> userCarts; // 장바구니 항목 리스트


  private String userName;  // 회원 이름
  private String userEmail;  // 회원 이메일
  private String userAccessLevel; // 회원권한

}
