package com.bbmore.order.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_member")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Member {
    @Id
    private int userCode;
    private String userId;
    private String userPassword;
    private String userName;
    private String userAddress;
    private String userMembershipLevel;
    private String userPhoneNumber;
    private String userEmail;
    private Boolean userIsdeleted;
    private String userAccessLevel;
}
