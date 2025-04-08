package com.bbmore.admin.amember.controller;

import com.bbmore.admin.amember.dto.AdminMemberDTO;
import com.bbmore.admin.amember.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    // 전체 회원 조회
    @GetMapping
    public List<AdminMemberDTO> getAllMembers() {
        return memberService.getAllMembers();
    }

    // 회원 검색 – 이름, 전화번호, 등급(멤버십 레벨) 기준
    @GetMapping("/search")
    public List<AdminMemberDTO> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String grade) {
        return memberService.searchMembers(name, phone, grade);
    }

    // 회원 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable Integer id,
                                             @RequestBody AdminMemberDTO dto) {
        AdminMemberDTO updatedDto = AdminMemberDTO.builder()
                .userCode(id)
                .userName(dto.getUserName())
                .userAddress(dto.getUserAddress())
                .userPhoneNumber(dto.getUserPhoneNumber())
                .userMembershipLevel(dto.getUserMembershipLevel())
                .animalBreed(dto.getAnimalBreed())
                .build();
        memberService.updateMember(updatedDto);
        return ResponseEntity.ok().build();
    }

    // 회원 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }
}
