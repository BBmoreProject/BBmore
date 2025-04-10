package com.bbmore.admin.amember.service;

import com.bbmore.admin.amember.dto.AdminMemberDTO;
import com.bbmore.admin.amember.repository.adminMemberRepository;
import com.bbmore.member.entity.Member;
import com.bbmore.member.entity.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class adminMemberService {

    private final adminMemberRepository adminMemberRepository;

    public List<AdminMemberDTO> getAllMembers() {
        return adminMemberRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AdminMemberDTO> searchMembers(String name, String phone, String grade) {
        return adminMemberRepository.searchMembers(name, phone, grade).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void saveMember(AdminMemberDTO dto) {
        adminMemberRepository.save(convertToEntity(dto));
    }

    public AdminMemberDTO updateMember(AdminMemberDTO dto) {
        Member existing = adminMemberRepository.findById(dto.getUserCode())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Member updated = existing.toBuilder()
                .userName(dto.getUserName())
                .userAddress(dto.getUserAddress())
                .userPhoneNumber(dto.getUserPhoneNumber())
                .animal(updateAnimal(existing.getAnimal(), dto.getAnimalBreed()))
                .build();

        return convertToDTO(adminMemberRepository.save(updated));
    }

    public Optional<AdminMemberDTO> getMemberById(Integer id) {
        return adminMemberRepository.findById(id).map(this::convertToDTO);
    }

    public void deleteMember(Integer id) {
        adminMemberRepository.deleteById(id);
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
