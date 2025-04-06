package com.bbmore.product.controller;

import com.bbmore.product.dto.ProductFormDto;
import com.bbmore.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /// View 에 반환
    @GetMapping(value = "/admin/product/new")
    public String productForm(Model model){
        model.addAttribute("productFormDto", new ProductFormDto());
        return "/product/productForm";
    }

    @PostMapping(value = "/admin/product/new")
    public String productNew(
            @Valid ProductFormDto productFormDto,
            BindingResult bindingResult, Model model,
            @RequestParam("productImgFile")List<MultipartFile> productImgFileList) {

        if(bindingResult.hasErrors()) {
            return "product/productForm";
        }

        if(productImgFileList.get(0).isEmpty() && productFormDto.getProductCode() == null) {
            model.addAttribute("errorMessage", "첫 번째 상품 이미지는 필수 입력 값입니다");
            return "product/productForm";
        }

        try {
            productService.saveProduct(productFormDto, productImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다");
            return "product/productForm";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/admin/product/{productCode}")
    public String productDtl(@PathVariable("productCode") Integer productCode, Model model) {

        try {
            ProductFormDto productFormDto = productService.getProductDtl(productCode);
            model.addAttribute("productFormDto", productFormDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("productFormDto", new ProductFormDto());
            return "product/productForm";
        }
        return "product/productForm";
    }
}
