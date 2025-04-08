package com.bbmore.admin.amember.controller;

import com.bbmore.admin.amember.dto.AdminMemberDTO;
import com.bbmore.admin.amember.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members/list")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 목록 조회
    @GetMapping
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "members/list";
    }

    // 회원 등록 화면
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("member", AdminMemberDTO.builder().build());
        return "members/form";
    }

    // 회원 등록 처리
    @PostMapping
    public String saveMember(@ModelAttribute AdminMemberDTO dto) {
        memberService.saveMember(dto);
        return "redirect:/members/list";
    }

    // 회원 수정 화면
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        AdminMemberDTO dto = memberService.getMemberById(id)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
        model.addAttribute("member", dto);
        return "members/form";
    }

    // 회원 수정 처리
    @PostMapping("/update/{id}")
    public String updateMember(@PathVariable Integer id,
                               @ModelAttribute AdminMemberDTO dto) {
        // AdminMemberDTO는 불변이므로, 업데이트 시 id를 포함한 새 DTO를 재생성합니다.
        AdminMemberDTO updatedDto = AdminMemberDTO.builder()
                .userCode(id)
                .userName(dto.getUserName())
                .userAddress(dto.getUserAddress())
                .userPhoneNumber(dto.getUserPhoneNumber())
                .userMembershipLevel(dto.getUserMembershipLevel())
                .animalBreed(dto.getAnimalBreed())
                .build();
        memberService.updateMember(updatedDto);
        return "redirect:/members/list";
    }

    // 회원 삭제
    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return "redirect:/members/list";
    }
}
