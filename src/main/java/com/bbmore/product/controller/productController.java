package com.bbmore.product.controller;

import com.bbmore.product.dto.ProductFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class productController {

    /**
     * 이 URL은 /admin/** 패턴에 해당하므로, 앞서 본 SecurityConfig 설정에 따라 ADMIN 역할을 가진 사용자만 접근
     * : Spring의 ViewResolver가 이 문자열을 해석해 실제 템플릿 위치를 찾습니다.
     * 앞의 슬래시(/)로 시작하므로 src/main/resources/templates/product/productForm.html 파일을 찾을 것입니다.
     *
     *
     */

    @GetMapping(value = "/admin/product/new")
    public String productForm(Model model) {
        model.addAttribute("productFormDTO", new ProductFormDTO());
        return "/product/productForm";
    }
}
