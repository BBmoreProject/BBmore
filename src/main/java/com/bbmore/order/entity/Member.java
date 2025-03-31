package com.bbmore.order.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tbl_member")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_code")
    private int userCode;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_membership_level")
    private String userMembershipLevel;

    @Column(name = "user_phone_number")
    private String userPhoneNumber;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_isdeleted")
    private boolean userIsDeleted;

    @ManyToOne
    @JoinColumn(name = "membership_code", referencedColumnName = "membership_code")
    private Membership membership; // Membership 테이블과 관계 설정

    @OneToMany(mappedBy = "member")
    private List<Order> orders; // Order 테이블과 관계 설정

}
