//package com.bbmore.admin.amember.service;
//
//import com.bbmore.admin.amember.dto.MemberApi;
//import com.bbmore.admin.amember.repository.MemberRepository;
//import com.bbmore.member.dto.MemberDTO;
//import com.bbmore.member.entity.Animal;
//import com.bbmore.member.entity.Member;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class MemberService {
//
//    private final MemberRepository memberRepository;
//
//    // 전체 회원 목록
//    public List<MemberDTO> getAllMembers() {
//        return memberRepository.findAll()
//                .stream()
//                .map(MemberApi::toDTO)   // Member -> MemberDTO
//                .collect(Collectors.toList());
//    }
//
//    // 회원 정보 업데이트
//    public MemberDTO updateMember(MemberDTO dto) {
//        Member existing = memberRepository.findById(dto.getUserCode())
//                .orElseThrow(() -> new RuntimeException("Member not found"));
//
//        // 기존 Animal 정보를 업데이트
//        Animal updatedAnimal = null;
//        if (dto.getAnimalDTO() != null) {
//            if (existing.getAnimal() != null) {
//                updatedAnimal = existing.getAnimal().toBuilder()
//                        .animalType(dto.getAnimalDTO().getAnimalType())
//                        .animalBreed(dto.getAnimalDTO().getAnimalBreed())
//                        .build();
//            } else {
//                updatedAnimal = Animal.builder()
//                        .animalType(dto.getAnimalDTO().getAnimalType())
//                        .animalBreed(dto.getAnimalDTO().getAnimalBreed())
//                        .build();
//            }
//        }
//
//        // Member 빌더를 사용해 변경 사항만 수정
//        Member updated = existing.toBuilder()
//                .userName(dto.getUserName())
//                .userAddress(dto.getUserAddress())
//                .userPhoneNumber(dto.getUserPhoneNumber())
//                .membership(existing.getMembership())
//                .animal(updatedAnimal)
//                .build();
//
//        Member saved = memberRepository.save(updated);
//        return MemberApi.toDTO(saved);
//    }
//
//    // 회원 검색
//    public List<MemberDTO> searchMembers(String name, String phone, String grade) {
//        return memberRepository.searchMembers(name, phone, grade)
//                .stream()
//                .map(MemberApi::toDTO)
//                .collect(Collectors.toList());
//    }
//
//
////    public List<MemberDTO> searchMembers(String name, String phone, String grade) {
////        return memberRepository.findAll()
////                .stream()
////                .map(MemberApi::toDTO)
////                .filter(member ->
////                        (name == null || name.isBlank() || member.getUserName().contains(name)) &&
////                                (phone == null || phone.isBlank() || member.getUserPhoneNumber().contains(phone)) &&
////                                (grade == null || grade.isBlank() ||
////                                        grade.equalsIgnoreCase(member.getUserMemberShipLevel()))
////                )
////                .collect(Collectors.toList());
////    }
//
//    // 회원 삭제
//    public void deleteMember(Integer id) {
//        memberRepository.deleteById(id);
//    }
//
//    // 회원 저장 (등록)
//    public void saveMember(MemberDTO dto) {
//        Member member = MemberApi.toEntity(dto);
//        memberRepository.save(member);
//    }
//
//    // 회원 단건 조회
//    public Optional<MemberDTO> getMemberById(Integer id) {
//        return memberRepository.findById(id)
//                .map(MemberApi::toDTO);
//    }
//}
