package com.bbmore.admin.amember.service;

import com.bbmore.admin.amember.dto.AdminMemberDTO;

import com.bbmore.admin.amember.api.MemberApi;

import com.bbmore.admin.amember.mapper.MemberMapper;

import com.bbmore.admin.amember.repository.MemberRepository;
import com.bbmore.member.entity.Animal;
import com.bbmore.member.entity.Member;
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
        return memberRepository.findAll()
                .stream()

                .map(MemberApi::toAdminDTO)

                .map(MemberMapper::toAdminDTO)

                .collect(Collectors.toList());
    }

    public AdminMemberDTO updateMember(AdminMemberDTO dto) {
        Member existing = memberRepository.findById(dto.getUserCode())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Animal updatedAnimal = null;
        if (dto.getAnimalBreed() != null && !dto.getAnimalBreed().isEmpty()) {
            updatedAnimal = (existing.getAnimal() != null)
                    ? existing.getAnimal().toBuilder().animalBreed(dto.getAnimalBreed()).build()
                    : Animal.builder().animalBreed(dto.getAnimalBreed()).build();
        }

        Member updated = existing.toBuilder()
                .userName(dto.getUserName())
                .userAddress(dto.getUserAddress())
                .userPhoneNumber(dto.getUserPhoneNumber())
                .userMembershipLevel(dto.getUserMembershipLevel())
                .animal(updatedAnimal)
                .build();


        return MemberApi.toAdminDTO(memberRepository.save(updated));
        return MemberMapper.toAdminDTO(memberRepository.save(updated));
    }

    public List<AdminMemberDTO> searchMembers(String name, String phone, String grade) {
        return memberRepository.findAll()
                .stream()

                .map(MemberApi::toAdminDTO)

                .map(MemberMapper::toAdminDTO)

                .filter(member ->
                        (name == null || name.isBlank() || member.getUserName().contains(name)) &&
                                (phone == null || phone.isBlank() || member.getUserPhoneNumber().contains(phone)) &&
                                (grade == null || grade.isBlank() || member.getUserMembershipLevel().equalsIgnoreCase(grade))
                )
                .collect(Collectors.toList());
    }

    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }

    public void saveAdminMember(AdminMemberDTO dto) {

        Member member = MemberApi.toEntity(dto);

        Member member = MemberMapper.toEntity(dto);

        memberRepository.save(member);
    }

    public Optional<AdminMemberDTO> getAdminMemberById(Integer id) {
        return memberRepository.findById(id)

                .map(MemberApi::toAdminDTO);

                .map(MemberMapper::toAdminDTO);
    }
}