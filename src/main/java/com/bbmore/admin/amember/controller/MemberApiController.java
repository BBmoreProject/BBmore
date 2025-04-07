//package com.bbmore.admin.amember.controller;
//
//import com.bbmore.admin.amember.service.MemberService;
//import com.bbmore.member.dto.MemberDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/members")
//@RequiredArgsConstructor
//public class MemberApiController {
//
//    private final MemberService memberService;
//
//    // 전체 회원 조회
//    @GetMapping
//    public List<MemberDTO> getAllMembers() {
//        return memberService.getAllMembers();
//    }
//
//    // 회원 검색 상단
//    @GetMapping("/search")
//    public List<MemberDTO> searchMembers(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String phone,
//            @RequestParam(required = false) String grade
//    ) {
//        return memberService.searchMembers(name, phone, grade);
//    }
//
//    // 회원 수정 모달
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateMember(@PathVariable Integer id,
//                                             @RequestBody MemberDTO dto) {
//        // PathVariable로 받은 id를 userCode로 세팅
//        dto.setUserCode(id);
//        memberService.updateMember(dto);
//        return ResponseEntity.ok().build();
//    }
//
//    // 회원 삭제 모달
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
//        memberService.deleteMember(id);
//        return ResponseEntity.ok().build();
//    }
//}
