    package com.bbmore.admin.amember.controller;

    import com.bbmore.admin.amember.dto.MemberDTO;
    import com.bbmore.admin.amember.service.MemberService;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @Controller
    @RequestMapping("/members/list")

    public class MemberController {

        private final MemberService memberService;

        public MemberController(MemberService memberService) {
            this.memberService = memberService;
        }

        @GetMapping
        public String listMembers(Model model) {
            List<MemberDTO> members = memberService.getAllMembers();
            model.addAttribute("members", members);
            return "members/list";
        }

        @GetMapping("/new")
        public String createForm(Model model) {
            model.addAttribute("member", new MemberDTO(null, null, null, null, null, null));
            return "members/form";
        }

        @PostMapping
        public String saveMember(@ModelAttribute MemberDTO dto) {
            memberService.saveMember(dto);
            return "redirect:/members";
        }

        @GetMapping("/edit/{id}")
        public String editForm(@PathVariable Integer id, Model model) {
            MemberDTO dto = memberService.getMemberById(id).orElseThrow();
            model.addAttribute("member", dto);
            return "members/form";
        }

        @PostMapping("/update/{id}")
        public String updateMember(@PathVariable Integer id, @ModelAttribute MemberDTO dto) {
            MemberDTO updated = MemberDTO.builder()
                    .userCode(id)
                    .userName(dto.getUserName())
                    .userAddress(dto.getUserAddress())
                    .userMembershipLevel(dto.getUserMembershipLevel())
                    .userPhoneNumber(dto.getUserPhoneNumber())
                    .animalBreed(dto.getAnimalBreed())  // DTO에서 받아온 animalBreed
                    .build();
            memberService.saveMember(updated);
            return "redirect:/members";
        }

        @GetMapping("/delete/{id}")
        public String deleteMember(@PathVariable Integer id) {
            memberService.deleteMember(id);
            return "redirect:/members";
        }
    }
