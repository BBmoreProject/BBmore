package com.bbmore.order.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
    private int userCode;
    private String userId;
    private String userPassword;
    private String userName;
    private String userAddress;
    private String userMembershipLevel;
    private String userPhoneNumber;
    private String userEmail;
    private int userIsDeleted;
    private String userAccessLevel;
    private int membershipCode;
    private int petCode;

}
