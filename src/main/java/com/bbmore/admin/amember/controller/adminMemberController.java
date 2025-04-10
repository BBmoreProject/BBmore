package com.bbmore.admin.amember.controller;

import com.bbmore.admin.amember.dto.AdminMemberDTO;
import com.bbmore.admin.amember.service.adminMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class adminMemberController {

    private final adminMemberService adminMemberService;

    // 📄 HTML 페이지 렌더링
    @GetMapping("/list")
    public String listMembersPage() {
        return "members/list"; // thymeleaf or jsp
    }

    // ✅ 전체 회원 조회 (JSON)
    @GetMapping("/api")
    @ResponseBody
    public List<AdminMemberDTO> getAllMembers() {
        return adminMemberService.getAllMembers();
    }

    // 🔍 검색 (이름, 전화번호, 등급)
    @GetMapping("/api/search")
    @ResponseBody
    public List<AdminMemberDTO> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String grade) {
        return adminMemberService.searchMembers(name, phone, grade);
    }

    // ✏️ 회원 수정
    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> updateMember(@PathVariable Integer id,
                                             @RequestBody AdminMemberDTO dto) {
        adminMemberService.updateMember(dto.toBuilder().userCode(id).build());
        return ResponseEntity.ok().build();
    }

    // ❌ 회원 삭제
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        adminMemberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }
}
