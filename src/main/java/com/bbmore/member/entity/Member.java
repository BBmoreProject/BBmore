package com.bbmore.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // -> 기본 생성자 접근 제한으로 무분별한 객체 생성 지양
// @Setter      // 지양 => 객체를 언제든지 변경할 수 있으면 객체의 안전성 보장 X
// 값 변경이 필요헌 경우에는 해당 기능을 위한 메소드를 별도로 생성
// @AllArgsConstructor
// 지양 => 인스턴스 멤버의 선언 순서에 영향을 받기 때문에 변수의 순서를 바꾸면 생성자의 입력 값 순서도 바뀌게 되어 검출되지 않는 오류를 발생시킬 수 있음.
// @ToString        // : 사용 가능하다 연관 관계 매핑 필드는 제거하고 사용
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_code")
    private Integer userCode;  // 회원고유번호

    @Column(name = "user_id", unique = true)
    private String userId;     // 회원ID

    @Column(name = "user_password")
    private String userPassword;   // 회원비밀번호

    @Column(name = "user_name")
    private String userName;       // 회원이름

    @Column(name = "user_address")
    private String userAddress;    // 회원주소

    @Column(name = "user_membership_level")
    private String userMembershipLevel;   // 회원등급

    @Column(name = "user_phone_number")
    private String userPhoneNumber;   // 회원전화번호

    @Column(name = "user_email", unique = true)
    private String userEmail;  // 회원이메일

    @Column(name = "user_isdeleted")
    private Boolean userIsdeleted; // 회원탈퇴여부

    @Column(name = "user_access_level")
    private String userAccessLevel;   // 회원권한

    @Column(name = "user_pet_name")
    private String userPetName;

    @Column(name = "user_pet_age")
    private Integer userPetAge;

    @Column(name = "user_pet_weight")
    private Integer userPetWeight;

    @Column(name = "user_pet_medical_history")
    private String userPetMedicalHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_code")
    private Animal animal;

    @Builder
    public Member(String userId, String userPassword, String userName, String userAddress,
                  String userPhoneNumber, String userEmail, String userPetName,
                  Integer userPetAge, Integer userPetWeight, String userPetMedicalHistory, Animal animal) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
        this.userPetName = userPetName;
        this.userPetAge = userPetAge;
        this.userPetWeight = userPetWeight;
        this.userPetMedicalHistory = userPetMedicalHistory;
        this.animal = animal;
    }


    // 기존 객체의 필드를 직접 수정하는 메서드 추가
    public void updateMemberInfo(String userPassword, String userName, String userAddress,
                                 String userPhoneNumber, String userEmail, String userPetName,
                                 Integer userPetAge, Integer userPetWeight, String userPetMedicalHistory,
                                 Animal animal) {
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
        this.userPetName = userPetName;
        this.userPetAge = userPetAge;
        this.userPetWeight = userPetWeight;
        this.userPetMedicalHistory = userPetMedicalHistory;
        this.animal = animal;
    }



}