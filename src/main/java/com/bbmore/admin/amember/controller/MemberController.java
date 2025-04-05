package com.bbmore.admin.amember.controller;

import org.springframework.ui.Model;
import com.bbmore.admin.amember.dto.AdminMemberDTO;
import com.bbmore.admin.amember.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members/list")
@RequiredArgsConstructor
class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "members/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("member", AdminMemberDTO.builder().build());
        return "members/form";
    }

    @PostMapping
    public String saveMember(@ModelAttribute AdminMemberDTO dto) {
        memberService.saveAdminMember(dto);
        return "redirect:/members/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        AdminMemberDTO dto = memberService.getAdminMemberById(id)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
        model.addAttribute("member", dto);
        return "members/form";
    }

    @PostMapping("/update/{id}")
    public String updateMember(@PathVariable Integer id, @ModelAttribute AdminMemberDTO dto) {
        AdminMemberDTO updated = AdminMemberDTO.builder()
                .userCode(id)
                .userName(dto.getUserName())
                .userAddress(dto.getUserAddress())
                .userMembershipLevel(dto.getUserMembershipLevel())
                .userPhoneNumber(dto.getUserPhoneNumber())
                .animalBreed(dto.getAnimalBreed())
                .build();
        memberService.updateMember(updated);
        return "redirect:/members/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return "redirect:/members/list";
    }
}