package com.bbmore.product.controller;

import com.bbmore.product.constant.ProductSellStatus;
import com.bbmore.product.dto.ProductFormDTO;
import com.bbmore.product.dto.ProductSearchDTO;
import com.bbmore.product.entity.Product;
import com.bbmore.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    @GetMapping(value = "/admin/product/{id}")
    public String ProductDetail(@PathVariable("id") Long ProductId, Model model) {
        try {
            ProductFormDTO productFormDTO = productService.getProductDetail(ProductId);

            // null 체크 및 기본값 설정 추가
            if (productFormDTO.getProductSellStatus() == null) {
                productFormDTO.setProductSellStatus(ProductSellStatus.SELL);
            }

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
    @PostMapping(value = "/admin/product/{id}")
    public String productUpdate(@Valid ProductFormDTO productFormDTO,
                                BindingResult bindingResult,
                                @RequestParam("productImgFile") List<MultipartFile> productImgFileList,
                                Model model) {

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
            // 로그에 구체적인 에러 메시지와 스택 트레이스를 남깁니다.
            log.error("상품 수정 중 에러가 발생했습니다", e);
            // e.printStackTrace(); // 필요 시 콘솔에 스택 트레이스 출력

            // 사용자에게 표시할 에러 메시지에 구체적인 내용 추가
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생했습니다: " + e.toString());
            return "product/productForm";
        }
        return "redirect:/";
    }

    @GetMapping(value = {"/admin/products", "/admin/products/{page}"})
    public String productManage(ProductSearchDTO productSearchDTO,
                                @PathVariable(value = "page", required = false) Optional<Integer> page,
                                Model model) {
        try {
            Pageable pageable = PageRequest.of(page.orElse(0), 5);
            Page<Product> products = productService.getAdminProductPage(productSearchDTO, pageable);

            // 상품 상태가 null인 경우 기본값 설정
            for (Product product : products.getContent()) {
                if (product.getProductSellStatus() == null) {
                    product.setProductSellStatus(ProductSellStatus.SELL);
                }
            }

            model.addAttribute("products", products);
            model.addAttribute("productSearchDTO", productSearchDTO);
            model.addAttribute("maxPage", 5);

            return "product/productMng";
        } catch (Exception e) {
            // 로깅 추가
            e.printStackTrace();
            model.addAttribute("errorMessage", "상품 목록을 불러오는 중 오류가 발생했습니다.");
            return "error/admin-error";
        }
    }
}
