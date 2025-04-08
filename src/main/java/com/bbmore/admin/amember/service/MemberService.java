package com.bbmore.admin.amember.service;

import com.bbmore.admin.amember.dto.AdminMemberDTO;
import com.bbmore.admin.amember.repository.MemberRepository;
import com.bbmore.member.entity.Member;
import com.bbmore.member.entity.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<AdminMemberDTO> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AdminMemberDTO> searchMembers(String name, String phone, String grade) {
        return memberRepository.searchMembers(name, phone, grade).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void saveMember(AdminMemberDTO dto) {
        memberRepository.save(convertToEntity(dto));
    }

    public AdminMemberDTO updateMember(AdminMemberDTO dto) {
        Member existing = memberRepository.findById(dto.getUserCode())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Member updated = existing.toBuilder()
                .userName(dto.getUserName())
                .userAddress(dto.getUserAddress())
                .userPhoneNumber(dto.getUserPhoneNumber())
                .animal(updateAnimal(existing.getAnimal(), dto.getAnimalBreed()))
                .build();
        return convertToDTO(memberRepository.save(updated));
    }

    public Optional<AdminMemberDTO> getMemberById(Integer id) {
        return memberRepository.findById(id).map(this::convertToDTO);
    }

    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }

    private AdminMemberDTO convertToDTO(Member m) {
        return AdminMemberDTO.builder()
                .userCode(m.getUserCode())
                .userName(m.getUserName())
                .userAddress(m.getUserAddress())
                .userPhoneNumber(m.getUserPhoneNumber())
                .userMembershipLevel(m.getMembership() != null ? m.getMembership().getMembershipName() : null)
                .animalBreed(m.getAnimal() != null ? m.getAnimal().getAnimalBreed() : null)
                .build();
    }

    private Member convertToEntity(AdminMemberDTO dto) {
        Member.MemberBuilder builder = Member.builder()
                .userName(dto.getUserName())
                .userAddress(dto.getUserAddress())
                .userPhoneNumber(dto.getUserPhoneNumber());
        if (dto.getAnimalBreed() != null) {
            builder.animal(Animal.builder().animalBreed(dto.getAnimalBreed()).build());
        }
        return builder.build();
    }

    private Animal updateAnimal(Animal animal, String breed) {
        if (animal != null) {
            return animal.toBuilder().animalBreed(breed).build();
        } else if (breed != null) {
            return Animal.builder().animalBreed(breed).build();
        }
        return null;
    }
}
