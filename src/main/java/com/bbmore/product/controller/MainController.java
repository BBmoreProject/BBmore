package com.bbmore.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    /**
     * 회원가입 완료 → return "redirect:/"; → 브라우저에 302 응답 전송
     * 브라우저가 /로 새 GET 요청 전송
     * MainController의 main() 메서드 실행
     * return "main"; → main.html 템플릿 렌더링
     */

    @GetMapping("/")
    public String main(){
        return "main";
    }
}