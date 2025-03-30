package com.bbmore.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userCode;  // 회원 고유 번호 (Primary Key)

  private String userName;  // 회원 이름
  private String userEmail;  // 회원 이메일

}
