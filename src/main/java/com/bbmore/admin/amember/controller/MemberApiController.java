package com.bbmore.admin.amember.controller;

import com.bbmore.admin.amember.service.MemberService;
import com.bbmore.member.dto.MemberDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;

    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberDTO> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/search")
    public List<MemberDTO> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String grade
    ) {
        return memberService.searchMembers(name, phone, grade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable Integer id, @RequestBody MemberDTO dto) {
        MemberDTO updated = memberService.updateMember(
                MemberDTO.builder()
                        .userCode(id)
                        .userName(dto.getUserName())
                        .userAddress(dto.getUserAddress())
                        .userMembershipLevel(dto.getUserMembershipLevel())
                        .userPhoneNumber(dto.getUserPhoneNumber())
                        .animalBreed(dto.getAnimalBreed())
                        .build()
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }
}
