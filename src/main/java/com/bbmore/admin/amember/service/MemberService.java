package com.bbmore.admin.amember.service;


import com.bbmore.admin.amember.dto.MemberDTO;
import com.bbmore.admin.amember.entity.Member;
import com.bbmore.admin.amember.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDTO saveMember(MemberDTO dto) {
        Member member = MemberDTO.toEntity(dto);
        return memberRepository.save(member).toDTO();
    }

    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(Member::toDTO)
                .toList();
    }

    public Optional<MemberDTO> getMemberById(Integer id) {
        return memberRepository.findById(id).map(Member::toDTO);
    }

    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }
}