package com.bbmore.admin.amember.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class MemberDTO
{
    private final Integer userCode;
    private final String userName;
    private final String userAddress;
    private final String userMembershipLevel;
    private final String userPhoneNumber;
    private final String animalBreed;

    public static com.bbmore.admin.amember.entity.Member toEntity(MemberDTO dto) {
        return com.bbmore.admin.amember.entity.Member.builder()
                .userCode(dto.getUserCode())
                .userName(dto.getUserName())
                .userAddress(dto.getUserAddress())
                .userMembershipLevel(dto.getUserMembershipLevel())
                .userPhoneNumber(dto.getUserPhoneNumber())
                .animalBreed(dto.getAnimalBreed())
                .build();
    }
}
