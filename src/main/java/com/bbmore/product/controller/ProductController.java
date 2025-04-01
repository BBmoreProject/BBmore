package com.bbmore.product.controller;

import com.bbmore.product.dto.ProductFormDTO;
import com.bbmore.product.dto.ProductSearchDTO;
import com.bbmore.product.entity.Product;
import com.bbmore.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.hibernate.loader.internal.AliasConstantsHelper.get;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    ///   /admin/product/new 경로로 오는 HTTP POST 요청을 이 메소드가 처리하도록 지정합니다.
    @PostMapping(value = "/admin/product/new")
    public String productNew(@Valid ProductFormDTO productFormDTO, BindingResult
            bindingResult, Model model, @RequestParam("productImgFile")
                             List<MultipartFile> productImgFileList){

        /**
         * @Valid ProductFormDTO productFormDTO: 유효성 검사를 수행할 상품 폼 DTO
         * BindingResult bindingResult: 유효성 검사 결과를 담는 객체
         * Model model: 뷰에 데이터를 전달하기 위한 모델 객체
         * @RequestParam("productImgFile") List<MultipartFile> productImgFileList:
         * "productImgFile"이라는 이름으로 전송된 여러 파일들
         */
        if(bindingResult.hasErrors()){
            return "product/productForm";
        }

        if(productImgFileList.get(0).isEmpty() && productFormDTO.getId() == null){
            model.addAttribute("errorMessage",
                    "대표 상품 이미지는 필수 입력 값입니다.");
            return "product/productForm";
        }

        try { ///  상품 서비스의 saveProduct 메소드를 호출하여 상품과 이미지를 저장합니다.
            productService.saveProduct(productFormDTO, productImgFileList);

        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다");
            return "product/productForm";
        }
        return "redirect:/";
    }

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

    @GetMapping(value = "/admin/product/{productId}")
    public String ProductDetail(@PathVariable("productId") Long ProductId, Model model) {

        try {
            ProductFormDTO productFormDTO = productService.getProductDetail(ProductId);
            model.addAttribute("productFormDTO", productFormDTO);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다");
            model.addAttribute("productFormDTO", new ProductFormDTO());
            return "/product/productForm";
        }
        return "/product/productForm";
    }

    /**
     * BindingResult: 유효성 검사 결과를 담는 객체입니다.
     * 앞서 @Valid로 검증한 결과가 이 객체에 저장됩니다.
     * 검증 오류가 있는지 확인하고, 있다면 적절한 처리를 할 수 있습니
     *
     *

     */
    @PostMapping(value = "/admin/product/{productId}")
    public String productUpdate(@Valid ProductFormDTO productFormDTO,
                                BindingResult bindingResult,
                                @RequestParam("productImgFile") List<MultipartFile>
                                productImgFileList, Model model) {

        if (bindingResult.hasErrors()) {
            return "product/productForm";
        }

        if(productImgFileList.get(0).isEmpty() && productFormDTO.getId() == null){
            model.addAttribute("errorMessage", "대표 상품 이미지는 필수 입력 값입니다.");
            return "product/productForm";
        }

        try {
            productService.updateProduct(productFormDTO, productImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생했습니다");
            return "product/productForm";
        }
        return "redirect:/";
    }

    @GetMapping(value = {"/admin/products", "/admin/products/{page}"})
    public String productManagement(ProductSearchDTO productSearchDTO,
                                    @PathVariable("page") Optional<Integer> page,
                                    Model model) {


        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<Product> products = productService.getAdminProductPage(productSearchDTO, pageable);
        model.addAttribute("products", products);
        model.addAttribute("productSearchDTO", productSearchDTO);
        model.addAttribute("maxPage", 5);
        return "product/productMng";

    }
}
