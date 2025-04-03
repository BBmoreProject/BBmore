package com.bbmore.product.service;


import com.bbmore.product.dto.MainProductDTO;
import com.bbmore.product.dto.ProductFormDTO;
import com.bbmore.product.dto.ProductImgDTO;
import com.bbmore.product.dto.ProductSearchDTO;
import com.bbmore.product.entity.Product;
import com.bbmore.product.entity.ProductImg;
import com.bbmore.product.repository.ProductImgRepository;
import com.bbmore.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Log
@Service
@Transactional
@RequiredArgsConstructor ///  Lombok 어노테이션으로, final로 선언된 필드를 매개변수로 받는
/// 생성자를 자동으로 생성
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImgRepository productImgRepository;
    private final ProductImgService productImgService;


    /**
     * 상품과 이미지를 저장하는 메소드를 선언합니다.
     * 상품 정보(DTO)와 이미지 파일 리스트를 매개변수로 받습니다.

     */
    public Long saveProduct(ProductFormDTO productFormDTO,
                            List<MultipartFile> productImgFileList) throws Exception {

        ///  상품 등록
        ///  DTO에서 제공하는 메소드를 통해 Product 엔티티 객체를 생성
        Product product = productFormDTO.createProduct();
        productRepository.save(product);

        /// 이미지 등록
        for (int i = 0; i < productImgFileList.size(); i++) {
            ProductImg productImg = new ProductImg();
            productImg.setProduct(product);

            /// 대표 이미지 여부 설정
            if (i == 0) {
                productImg.setRepresentativeImg("Y");
            } else {
                productImg.setRepresentativeImg("N");
            }
            /// 모든 이미지(대표 이미지 포함)를 저장
            productImgService.saveProductImg(productImg, productImgFileList.get(i));
        }
        return product.getId();
    }


    @Transactional(readOnly = true)
    /// 상품 ID를 매개변수로 받아 ProductFormDTO 객체를 반환하는 메소드를 선언합니다.
    public ProductFormDTO getProductDetail(Long id){

        List<ProductImg> productImgList =
                productImgRepository.findByProductIdOrderByIdAsc(id);
        ///  조회한 ProductImg 엔티티들을 DTO 변환하여 담을 빈 리스트를 생성합니다.
        List<ProductImgDTO> productImgDTOList = new ArrayList<>();
        for(ProductImg productImg : productImgList) {
            /**
             * 각 ProductImg 엔티티를 ProductImgDTO 객체로 변환합니다.
             * ProductImgDTO.of()는 정적 팩토리 메소드로,
             * 엔티티를 DTO로 변환하는 역할을 합니다.
             */
            ProductImgDTO productImgDTO = ProductImgDTO.of(productImg);
            productImgDTOList.add(productImgDTO);
        }

        Product product = productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        ProductFormDTO productFormDTO = ProductFormDTO.of(product);
        productFormDTO.setProductImgDTOList(productImgDTOList);

        return productFormDTO;

    }

    public Long updateProduct(ProductFormDTO productFormDTO,
                              List<MultipartFile> productImgFileList) throws Exception {

        log.info("업데이트할 상품 id: " + productFormDTO.getId());


        ///  product modify
        Product product = productRepository.findById(productFormDTO.getId())
                .orElseThrow(EntityNotFoundException::new);
        product.updateProduct(productFormDTO);

        List<Long> productImgIdList = productFormDTO.getProductImgIdList();

        for(int i=0; i < productImgFileList.size(); i++){
            productImgService.updateProductImg(productImgIdList.get(i),
                    productImgFileList.get(i));
        }
        return product.getId();

    }

    @Transactional(readOnly = true)
    public Page<Product> getAdminProductPage(ProductSearchDTO productSearchDTO,
                                      Pageable pageable) {
        return productRepository.getAdminProductPage(productSearchDTO, pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainProductDTO> getMainProductPage(ProductSearchDTO productSearchDTO,
                                      Pageable pageable) {
        return productRepository.getMainProductPage(productSearchDTO, pageable);
    }

}