package com.bbmore.member.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipDTO {

    private Integer membershipCode;              // 회원등급고유번호

    private String membershipName;                 // 회원등급명

}