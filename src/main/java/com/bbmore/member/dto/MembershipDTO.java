package com.bbmore.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MembershipDTO {

    private Integer membershipCode;              // 회원등급고유번호

    private String membershipName;                 // 회원등급명

}