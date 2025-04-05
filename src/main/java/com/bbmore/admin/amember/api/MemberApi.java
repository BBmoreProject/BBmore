package com.bbmore.admin.amember.api;

import com.bbmore.member.dto.AnimalDTO;
import com.bbmore.member.dto.MemberDTO;
import com.bbmore.member.entity.Animal;
import com.bbmore.member.entity.Member;

public class MemberApi {

    // Entity -> DTO
    public static MemberDTO toDTO(Member member) {
        if (member == null) {
            return null;
        }
        MemberDTO dto = new MemberDTO();
        dto.setUserCode(member.getUserCode());
        dto.setUserId(member.getUserId());
        dto.setUserPassword(member.getUserPassword());
        dto.setUserName(member.getUserName());
        dto.setUserAddress(member.getUserAddress());
        dto.setUserPhoneNumber(member.getUserPhoneNumber());
        dto.setUserEmail(member.getUserEmail());
        dto.setUserPetName(member.getUserPetName());
        dto.setUserPetAge(member.getUserPetAge());
        dto.setUserPetWeight(member.getUserPetWeight());
        dto.setUserPetMedicalHistory(member.getUserPetMedicalHistory());
        dto.setUserMemberShipLevel(member.getUserMemberShipLevel()); // 기존 AdminMemberDTO가 갖고 있던 필드

        // Animal -> AnimalDTO
        if (member.getAnimal() != null) {
            Animal animal = member.getAnimal();
            AnimalDTO animalDTO = new AnimalDTO();
            animalDTO.setAnimalCode(animal.getAnimalCode());
            animalDTO.setAnimalType(animal.getAnimalType());
            animalDTO.setAnimalBreed(animal.getAnimalBreed());
            dto.setAnimalDTO(animalDTO);
        }

        return dto;
    }

    // DTO -> Entity
    public static Member toEntity(MemberDTO dto) {
        if (dto == null) {
            return null;
        }
        Animal animal = null;
        if (dto.getAnimalDTO() != null) {
            AnimalDTO animalDTO = dto.getAnimalDTO();
            animal = Animal.builder()
                    .animalCode(animalDTO.getAnimalCode())
                    .animalType(animalDTO.getAnimalType())
                    .animalBreed(animalDTO.getAnimalBreed())
                    .build();
        }

        return Member.builder()
                .userCode(dto.getUserCode())
                .userId(dto.getUserId())
                .userPassword(dto.getUserPassword())
                .userName(dto.getUserName())
                .userAddress(dto.getUserAddress())
                .userPhoneNumber(dto.getUserPhoneNumber())
                .userEmail(dto.getUserEmail())
                .userMemberShipLevel(dto.getUserMemberShipLevel())
                .userPetName(dto.getUserPetName())
                .userPetAge(dto.getUserPetAge())
                .userPetWeight(dto.getUserPetWeight())
                .userPetMedicalHistory(dto.getUserPetMedicalHistory())
                .animal(animal)
                .build();
    }
}
