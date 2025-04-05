package com.bbmore.member.dto;

import com.bbmore.member.entity.Member;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {

    private Integer userCode;              // 회원고유번호

    private String userId;                 // 회원ID

    private String userPassword;           // 회원비밀번호

    private String userName;               // 회원이름

    private String userAddress;            // 회원주소
    
    private String userMembershipLevel;     // 회원등급 // 추가

    private String userPhoneNumber;        // 회원전화번호

    private String userEmail;              // 회원이메일
    
    private Boolean userIsdeleted;          // 회원탈퇴여부   // 추가
    
    private String userAccessLevel;         // 회원권한     // 추가

    private String userPetName;

    private Integer userPetAge;

    private Integer userPetWeight;

    private String userPetMedicalHistory;


}
