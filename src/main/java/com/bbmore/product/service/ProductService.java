package com.bbmore.product.service;

import com.bbmore.product.dto.ProductFormDto;
import com.bbmore.product.dto.ProductImgDto;
import com.bbmore.product.entity.Product;
import com.bbmore.product.entity.ProductImg;
import com.bbmore.product.repository.ProductImgRepository;
import com.bbmore.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImgService productImgService;
    private final ProductImgRepository productImgRepository;

    public Integer saveProduct(ProductFormDto productFormDto,
                               List<MultipartFile> productImgFileList) throws Exception {

        // 상품 등록
        Product product = productFormDto.createProduct();
        productRepository.save(product);

        for (int i = 0; i < productImgFileList.size(); i++) {
            ProductImg productImg = new ProductImg();
            productImg.setProduct(product);
            if (i == 0) {
                productImg.setProductImgRepYn("Y");
            } else {
                productImg.setProductImgRepYn("N");
            }
            productImgService.saveProductImg(productImg, productImgFileList.get(i));
            }
        return product.getProductCode();
    }


    /// 등록된 상품을 불러옴
    @Transactional(readOnly = true) ///  읽기 전용(더티체킹을 수행하지 않음)
    public ProductFormDto getProductDtl(Integer productCode) {

        List<ProductImg> productImgList =
                productImgRepository.findByProductProductCodeOrderByProductImgCodeAsc(productCode);
        List<ProductImgDto> productImgDtoList = new ArrayList<>();
        for(ProductImg productImg : productImgList) {
            ProductImgDto productImgDto = ProductImgDto.of(productImg);
            productImgDtoList.add(productImgDto);
        }

        Product product = productRepository.findById(productCode)
                .orElseThrow(EntityNotFoundException::new);
        ProductFormDto productFormDto = ProductFormDto.of(product);
        productFormDto.setProductImgDtoList(productImgDtoList);
        return productFormDto;
    }
    
    public Integer updateProduct(ProductFormDto productFormDto,
                                 List<MultipartFile> productImgFileList) throws Exception {

        Product product = productRepository.findById(productFormDto.getProductCode())
                .orElseThrow(EntityNotFoundException::new);
        product.updateProduct(productFormDto);

        List<Integer> productImgCodeList = productFormDto.getProductImgCodeList();
        for(int i = 0; i < productImgFileList.size(); i++) {
            productImgService.updateProductImg(productImgCodeList.get(i),
                    productImgFileList.get(i));
        }
        return product.getProductCode();
    }
}
