package com.bbmore.member.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Table(name = "tbl_member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_code")
    private Integer userCode;  // 회원고유번호

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;     // 회원ID

    @Column(name = "user_password", nullable = false)
    private String userPassword;   // 회원비밀번호

    @Column(name = "user_name", nullable = false)
    private String userName;       // 회원이름

    @Column(name = "user_address", nullable = false)
    private String userAddress;    // 회원주소

    @Column(name = "user_phone_number", nullable = false)
    private String userPhoneNumber;   // 회원전화번호

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;  // 회원이메일

    @Column(name = "user_isdeleted", nullable = false)
    private Boolean userIsdeleted; // 회원탈퇴여부

//    @Column(name = "user_access_level", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private UserRole userAccessLevel;   // 회원권한

    @Column(name = "user_pet_name", nullable = false)
    private String userPetName;     // 회원반려이름

    @Column(name = "user_pet_age", nullable = false)
    private Integer userPetAge;     // 회원반려나이

    @Column(name = "user_pet_weight", nullable = false)
    private Integer userPetWeight;      // 회원반려몸무게

    @Column(name = "user_pet_medical_history")
    private String userPetMedicalHistory;       // 회원반려병력



    // fk(동물고유번호)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_code")
    private Animal animal;

    // fk(회원등급고유번호)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_code")
    private Membership membership;





}