package com.bbmore.member.dto;

import com.bbmore.member.common.UserRole;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    private Integer userCode;              // 회원고유번호

    private String userId;                 // 회원ID

    private String userPassword;           // 회원비밀번호

    private String userName;               // 회원이름

    private String userAddress;            // 회원주소

    private String userPhoneNumber;        // 회원전화번호

    private String userEmail;              // 회원이메일

    private Boolean userIsdeleted;         // 회원탈퇴여부

    private UserRole userAccessLevel;      // 회원권한

    private String userPetName;            // 회원반려이름

    private Integer userPetAge;            // 회원반려나이

    private Integer userPetWeight;         // 회원반려몸무게

    private String userPetMedicalHistory;  // 회원반려병력


    private Integer animalCode;            // fk 동물고유번호

    private Integer membershipCode;        // fk 회원등급고유번호


}
