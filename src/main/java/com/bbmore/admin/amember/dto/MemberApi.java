//package com.bbmore.admin.amember.dto;
//
//import com.bbmore.member.dto.AnimalDTO;
//import com.bbmore.member.dto.MemberDTO;
//import com.bbmore.member.dto.MembershipDTO;
//import com.bbmore.member.entity.Animal;
//import com.bbmore.member.entity.Member;
//import com.bbmore.member.entity.Membership;
//
//public class MemberApi {
//
//    // Entity -> DTO
//    public static MemberDTO toDTO(Member member) {
//        if (member == null) {
//            return null;
//        }
//        MemberDTO dto = new MemberDTO();
//        dto.setUserCode(member.getUserCode());
//        dto.setUserName(member.getUserName());
//        dto.setUserAddress(member.getUserAddress());
//        dto.setUserPhoneNumber(member.getUserPhoneNumber());
//
//        // Animal -> AnimalDTO
//        if (member.getAnimal() != null) {
//            Animal animal = member.getAnimal();
//            AnimalDTO animalDTO = new AnimalDTO();
//            animalDTO.setAnimalCode(animal.getAnimalCode());
//            animalDTO.setAnimalType(animal.getAnimalType());
//            animalDTO.setAnimalBreed(animal.getAnimalBreed());
//            dto.setAnimalDTO(animalDTO);
//        }
//
//        // Membership
//        if (member.getMembership() != null) {
//            Membership membership = member.getMembership();
//            MembershipDTO membershipDTO = new MembershipDTO();
//            membershipDTO.setMembershipCode(membership.getMembershipCode());
//            membershipDTO.setMembershipName(membership.getMembershipName());
//            dto.setMembershipDTO(membershipDTO);
//        }
//
//        return dto;
//    }
//
//    // DTO -> Entity
//    public static Member toEntity(MemberDTO dto) {
//        if (dto == null) {
//            return null;
//        }
//        Animal animal = null;
//        if (dto.getAnimalDTO() != null) {
//            AnimalDTO animalDTO = dto.getAnimalDTO();
//            animal = Animal.builder()
//                    .animalCode(animalDTO.getAnimalCode())
//                    .animalType(animalDTO.getAnimalType())
//                    .animalBreed(animalDTO.getAnimalBreed())
//                    .build();
//        }
//
//        return Member.builder()
//                .userCode(dto.getUserCode())
//                .userName(dto.getUserName())
//                .userAddress(dto.getUserAddress())
//                .userPhoneNumber(dto.getUserPhoneNumber())
//                .membership(Membership.builder().build())
//                .animal(animal)
//                .build();
//    }
//}
