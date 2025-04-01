package com.bbmore.admin.amember.dto;

import com.bbmore.admin.amember.entity.Animal;
import com.bbmore.admin.amember.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class MemberDTO {
    private final Integer userCode;
    private final String userName;
    private final String userAddress;
    private final String userMembershipLevel;
    private final String userPhoneNumber;
    private final String animalBreed;

    public static Member toEntity(MemberDTO dto) {
        Member.MemberBuilder builder = Member.builder()
                .userCode(dto.getUserCode())
                .userName(dto.getUserName())
                .userAddress(dto.getUserAddress())
                .userMembershipLevel(dto.getUserMembershipLevel())
                .userPhoneNumber(dto.getUserPhoneNumber());

        if (dto.getAnimalBreed() != null && !dto.getAnimalBreed().isEmpty()) {
            Animal animal = Animal.builder()
                    .animalBreed(dto.getAnimalBreed())
                    .build();
            builder.animal(animal);
        }

        return builder.build();
    }

}
