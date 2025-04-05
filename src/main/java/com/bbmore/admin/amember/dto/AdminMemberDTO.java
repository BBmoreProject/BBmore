package com.bbmore.admin.amember.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class AdminMemberDTO {
    private final Integer userCode;
    private final String userName;
    private final String userAddress;
    private final String userPhoneNumber;
    private final String userMembershipLevel;
    private final String animalBreed;
}