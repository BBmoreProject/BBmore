package com.bbmore.admin.amember.mapper;

import com.bbmore.admin.amember.dto.AdminMemberDTO;
import com.bbmore.member.entity.Member;
import com.bbmore.member.entity.Animal;

public class MemberMapper {

    public static AdminMemberDTO toAdminDTO(Member member) {
        Animal animal = member.getAnimal();
        return AdminMemberDTO.builder()
                .userCode(member.getUserCode())
                .userName(member.getUserName())
                .userAddress(member.getUserAddress())
                .userPhoneNumber(member.getUserPhoneNumber())
                .userMembershipLevel(member.getUserMembershipLevel())
                .animalBreed(animal != null ? animal.getAnimalBreed() : null)
                .build();
    }

    public static Member toEntity(AdminMemberDTO dto) {
        Animal animal = null;
        if (dto.getAnimalBreed() != null && !dto.getAnimalBreed().isEmpty()) {
            animal = Animal.builder()
                    .animalBreed(dto.getAnimalBreed())
                    .build();
        }
        return Member.builder()
                .userCode(dto.getUserCode())
                .userName(dto.getUserName())
                .userAddress(dto.getUserAddress())
                .userPhoneNumber(dto.getUserPhoneNumber())
                .userMembershipLevel(dto.getUserMembershipLevel())
                .animal(animal)
                .build();
    }
}