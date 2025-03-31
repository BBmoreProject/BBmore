package com.bbmore.product.service;


import com.bbmore.product.dto.ProductFormDTO;
import com.bbmore.product.entity.Product;
import com.bbmore.product.entity.ProductImg;
import com.bbmore.product.repository.ProductImgRepository;
import com.bbmore.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

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
        return product.getProductId();
    }
}
