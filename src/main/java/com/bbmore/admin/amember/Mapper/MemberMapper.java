package com.bbmore.admin.amember.mapper;

import com.bbmore.member.entity.Member;      // 팀원꺼
import com.bbmore.member.entity.Animal;      // 팀원꺼
import com.bbmore.member.dto.MemberDTO;      // 이것도 팀원꺼

public class MemberMapper {

    public static MemberDTO toDTO(Member member) {
        Animal animal = member.getAnimal();

        return MemberDTO.builder()
                .userCode(member.getUserCode())
                .userName(member.getUserName())
                .userAddress(member.getUserAddress())
                .userMembershipLevel(member.getUserMembershipLevel())
                .userPhoneNumber(member.getUserPhoneNumber())
                .animalBreed(animal != null ? animal.getAnimalBreed() : null)
                .build();
    }
}
