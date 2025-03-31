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

    private boolean userIsDeleted;
    private boolean userAccessLevel;


    private String userPetName;
    private int userPetAge;
    private int userPerWeight;
    private String userPetMedicalHistory;

    private int membershipCode;
    private int animalCode;


}
