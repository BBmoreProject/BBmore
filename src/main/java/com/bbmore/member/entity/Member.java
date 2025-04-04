package com.bbmore.member.entity;

import com.bbmore.order.entity.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_member")
@Getter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
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

    // 04.04 태민 추가
    @ManyToOne
    @JoinColumn(name = "membership_code", referencedColumnName = "membership_code")
    private Membership membership; // Membership 테이블과 관계 설정

    @OneToMany(mappedBy = "member")
    private List<Order> orders; // Order 테이블과 관계 설정







}