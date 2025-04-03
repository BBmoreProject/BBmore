package com.bbmore.product.controller;

import com.bbmore.product.dto.MainProductDTO;
import com.bbmore.product.dto.ProductSearchDTO;
import com.bbmore.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    /**
     * 회원가입 완료 → return "redirect:/"; → 브라우저에 302 응답 전송
     * 브라우저가 /로 새 GET 요청 전송
     * MainController의 main() 메서드 실행
     * return "main"; → main.html 템플릿 렌더링
     */
    private final ProductService productService;

    @GetMapping("/")
    public String main(ProductSearchDTO productSearchDTO, Optional<Integer> page, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MainProductDTO> products =
                productService.getMainProductPage(productSearchDTO, pageable);
        model.addAttribute("products", products);
        model.addAttribute("productSearchDTO", productSearchDTO);
        model.addAttribute("maxPage", 5);
        return "main";
    }
}