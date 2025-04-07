//package com.bbmore.admin.amember.controller;
//
//import com.bbmore.admin.amember.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/members/list")
//@RequiredArgsConstructor
//public class MemberController {     // View
//
//    private final MemberService memberService;
//
//    // 회원 목록
//    @GetMapping
//    public String listMembers(Model model) {
//        model.addAttribute("members", memberService.getAllMembers());
//        return "members/list";
//    }

//    // 회원 등록 화면
//    @GetMapping("/new")
//    public String createForm(Model model) {
//        model.addAttribute("member", new MemberDTO());
//        return "members/form";
//    }
//
//    // 회원 등록 처리
//    @PostMapping
//    public String saveMember(@ModelAttribute MemberDTO dto) {
//        memberService.saveMember(dto);
//        return "redirect:/members/list";
//    }

//    // 회원 수정 화면
//    @GetMapping("/edit/{id}")
//    public String editForm(@PathVariable Integer id, Model model) {
//        MemberDTO dto = memberService.getMemberById(id)
//                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
//        model.addAttribute("member", dto);
//        return "members/form";
//    }

//    // 회원 수정 처리
//    @PostMapping("/update/{id}")
//    public String updateMember(@PathVariable Integer id,
//                               @ModelAttribute MemberDTO dto) {
//        dto.setUserCode(id);
//        memberService.updateMember(dto);
//        return "redirect:/members/list";
//    }

//    // 회원 삭제
//    @GetMapping("/delete/{id}")
//    public String deleteMember(@PathVariable Integer id) {
//        memberService.deleteMember(id);
//        return "redirect:/members/list";
//    }
//}
