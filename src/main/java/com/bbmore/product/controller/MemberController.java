package com.bbmore.product.controller;

import com.bbmore.product.dto.MemberFormDTO;
import com.bbmore.product.entity.Member;
import com.bbmore.product.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 더 명확한 어노테이션
 * @GetMapping("/members") - GET 요청만 처리
 * @PostMapping("/members") - POST 요청만 처리
 * @PutMapping("/members") - PUT 요청만 처리
 * @DeleteMapping("/members") - DELETE 요청만 처리
 */
@RequestMapping("/members") ///  모든 메소드는 /members로 시작하는 URL에 매핑
@Controller
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder; /// 비밀번호 암호화를 처리하는 객체

    @Autowired
    public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Model 객체는 뷰에 데이터를 전달하는 역할
     * 새로운 MemberFormDTO 객체를 생성해 모델에 추가
     * 뷰에서 memberFormDTO 라는 이름으로 새 DTO객체에 접근
     * member/memberForm 이라는 뷰를 반환
     * rc/main/resources/templates/member/memberForm.html
     */
    @GetMapping(value = "/new") ///  /new URL 경로로 들어오는 HTTP GET 요청을 이 메서드가 처리하도록 매핑
    public String memberForm(Model model) {
        model.addAttribute("memberFormDTO", new MemberFormDTO());
        return "member/memberForm";
    }
    /**
     * 비어있는 새로운 MemberFormDTO 객체를 생성합니다.
     * 이 객체를 "memberFormDTO"라는 이름으로 모델에 추가합니다.
     * 이 객체는 단지 빈 폼을 표시하기 위한 템플릿용
     * 이렇게 하면 Thymeleaf 템플릿에서 이 객체에 접근할 수 있습니다
     */

    /**
     * PostMapping
     데이터를 요청 본문(body)에 포함시켜 전송
     URL에 데이터가 노출되지 않습니다.
     */

        /**
         * Member 클래스의 정적 메서드 createMember를 호출하여 DTO에서 실제 엔티티 객체를 생성
         * 생성된 Member 객체를 데이터베이스에 저장
         * 회원가입 처리 후 메인 페이지("/")로 리다이렉트
         * "redirect:" 접두사는 뷰 이름이 아닌 리다이렉트할 URL
         */

        /** 코드의 흐름:
         GET 요청 처리: 사용자가 /members/new에 접속하면 GET 요청이 발생하고, memberForm.html이 렌더링됩니다.
         정보 입력 및 제출: 사용자가 폼에 정보를 입력하고 제출 버튼을 클릭하면 동일한 URL(/members/new)로 POST 요청이 발생합니다.
         DTO로 정보 바인딩: POST 요청에서 전송된 폼 데이터는 @ModelAttribute 어노테이션을 통해 MemberFormDTO 객체로 자동 바인딩됩니다.
         엔티티 생성 및 암호화: Member.createMember() 정적 메서드를 호출하여 DTO 값으로부터 새 엔티티를 생성하고, 이 과정에서 passwordEncoder를 사용해 비밀번호를 암호화합니다.
         데이터베이스 저장: memberService.saveMember(member)를 호출하여 생성된 회원 엔티티를 데이터베이스에 저장합니다.
         리다이렉트: 저장 후 redirect:/ 명령을 통해 메인 페이지로 리다이렉트합니다.
         */


    @PostMapping(value = "/new")
    public String newMember(@Valid MemberFormDTO memberFormDTO,
                            BindingResult bindingResult, Model model) {
        ///  @Valid DTO에 정의된 유효성 검증 수행
        ///  BindingResult : 유효성 검증 결과가 이 객체에 저장

        if (bindingResult.hasErrors()) {
            return "member/memberForm"; /// 회원가입으로 다시 보냄
        }
        try {
            Member member = Member.createMember(memberFormDTO, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            return "member/memberForm";
        }
        return "redirect:/";
    }

    /**
     * members/login URL에 대한 GET 요청을 처리합니다. (클래스에 @RequestMapping("/members")가 있으므로
     */

    @GetMapping(value = "/login")
    public String loginMember() {
        return "/member/memberLoginForm";
    }

    @GetMapping(value = "login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg",  "아이디 또는 비밀번호를 확인해주세요");
        return "member/memberLoginForm";

    }
}
