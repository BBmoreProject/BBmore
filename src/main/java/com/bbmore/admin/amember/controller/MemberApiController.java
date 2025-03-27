package com.bbmore.admin.amember.controller;

import com.bbmore.admin.amember.dto.MemberDTO;
import com.bbmore.admin.amember.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;

    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 전체 조회
    @GetMapping
    public List<MemberDTO> getAllMembers() {
        return memberService.getAllMembers();
    }

    // 검색 (이름, 전화번호, 등급)
    @GetMapping("/search")
    public List<MemberDTO> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String grade
    ) {
        return memberService.getAllMembers().stream()
                .filter(member -> (name == null || member.getUserName().contains(name))
                        && (phone == null || member.getUserPhoneNumber().contains(phone))
                        && (grade == null || member.getUserMembershipLevel().equalsIgnoreCase(grade)))
                .collect(Collectors.toList());
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable Integer id, @RequestBody MemberDTO dto) {
        MemberDTO updated = MemberDTO.builder()
                .userCode(id)
                .userName(dto.getUserName())
                .userAddress(dto.getUserAddress())
                .userMembershipLevel(dto.getUserMembershipLevel())
                .userPhoneNumber(dto.getUserPhoneNumber())
                .animalBreed(dto.getAnimalBreed())  // DTO에서 받아온 animalBreed
                .build();
        memberService.saveMember(updated);
        return ResponseEntity.ok().build();
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }
}