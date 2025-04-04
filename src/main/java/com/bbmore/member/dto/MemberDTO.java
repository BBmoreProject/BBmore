package com.bbmore.member.dto;

import lombok.*;


@Getter
@Setter
@ToString
// 04.04 태민 추가
@Builder
// 04.04 태민 추가
@AllArgsConstructor
@NoArgsConstructor

public class MemberDTO {

    private Integer userCode;              // 회원고유번호

    private String userId;                 // 회원ID

    private String userPassword;           // 회원비밀번호

    private String userName;               // 회원이름

    private String userAddress;            // 회원주소

    private String userMembershipLevel;    // 04.04 태민 추가

    private String userPhoneNumber;        // 회원전화번호

    private String userEmail;              // 회원이메일

    private String userPetName;

    private Integer userPetAge;

    private Integer userPetWeight;

    private String userPetMedicalHistory;

    private Integer membershipCode;           // 04.04 태민 추가
    private Integer animalCode;               // 04.04 태민 추가


}
