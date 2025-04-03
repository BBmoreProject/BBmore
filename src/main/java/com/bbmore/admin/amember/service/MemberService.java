package com.bbmore.admin.amember.service;

import com.bbmore.admin.amember.dto.MemberDTO;
import com.bbmore.admin.amember.entity.Animal;
import com.bbmore.admin.amember.entity.Member;
import com.bbmore.admin.amember.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 모든 회원 조회
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(member -> member.toDTO())
                .collect(Collectors.toList());
    }

    // 회원 업데이트
    public MemberDTO updateMember(MemberDTO dto) {
        Member existingMember = memberRepository.findById(dto.getUserCode())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Member.MemberBuilder memberBuilder = existingMember.toBuilder()
                .userName(dto.getUserName())
                .userAddress(dto.getUserAddress())
                .userMembershipLevel(dto.getUserMembershipLevel())
                .userPhoneNumber(dto.getUserPhoneNumber());

        if (dto.getAnimalBreed() != null && !dto.getAnimalBreed().isEmpty()) {
            if (existingMember.getAnimal() != null) {
                Animal updatedAnimal = existingMember.getAnimal().toBuilder()
                        .animalBreed(dto.getAnimalBreed())
                        .build();
                memberBuilder.animal(updatedAnimal);
            } else {
                memberBuilder.animal(
                        Animal.builder()
                                .animalBreed(dto.getAnimalBreed())
                                .build()
                );
            }
        } else {
            memberBuilder.animal(existingMember.getAnimal());
        }

        Member updatedMember = memberBuilder.build();
        return memberRepository.save(updatedMember).toDTO();
    }

    // searchMembers 메서드 추가
    public List<MemberDTO> searchMembers(String name, String phone, String grade) {
        return memberRepository.findAll()
                .stream()
                .map(Member::toDTO)
                .filter(member ->
                        (name == null || name.isEmpty() || member.getUserName().contains(name)) &&
                                (phone == null || phone.isEmpty() || member.getUserPhoneNumber().contains(phone)) &&
                                (grade == null || grade.isEmpty() || member.getUserMembershipLevel().equalsIgnoreCase(grade))
                )
                .collect(Collectors.toList());
    }

    // deleteMember 메서드 추가
    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }

    // 회원 저장 메서드 (신규 등록)
    public MemberDTO saveMember(MemberDTO dto) {
        Member member = MemberDTO.toEntity(dto);
        return memberRepository.save(member).toDTO();
    }

    // 특정 회원 조회 메서드
    public Optional<MemberDTO> getMemberById(Integer id) {
        return memberRepository.findById(id).map(Member::toDTO);
    }

}
