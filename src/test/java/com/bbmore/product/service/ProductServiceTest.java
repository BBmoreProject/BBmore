package com.bbmore.product.service;

import com.bbmore.product.constant.ProductSellStatus;
import com.bbmore.product.dto.ProductFormDto;
import com.bbmore.product.entity.Product;
import com.bbmore.product.entity.ProductImg;
import com.bbmore.product.repository.ProductImgRepository;
import com.bbmore.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImgRepository productImgRepository;

    List<MultipartFile> createMultipartFiles() throws Exception {

        List<MultipartFile> multipartFileList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String path = "file:///Users/kimdonghyeon/shop/product/";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile =
                    new MockMultipartFile(path, imageName,
                            "image/jpg", new byte[]{1,2,3,4});
            multipartFileList.add(multipartFile);
        }
        return multipartFileList;
    }

    @Test
    @DisplayName("상품 등록 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void saveProduct() throws Exception {
        ProductFormDto productFormDto = new ProductFormDto();
        productFormDto.setProductName("테스트상품");
        productFormDto.setProductSellStatus(ProductSellStatus.SELL);
        productFormDto.setProductDetail("테스트 상품 입니다");
        productFormDto.setProductPrice(1000);
        productFormDto.setProductQuantity(100);

        List<MultipartFile> multipartFileList = createMultipartFiles();
        Integer productCode = productService.saveProduct(productFormDto, multipartFileList);

        List<ProductImg> productImgList =
                productImgRepository.findByProductProductCodeOrderByProductImgCodeAsc(productCode);
        Product product = productRepository.findById(productCode)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(productFormDto.getProductName(), product.getProductName());
        assertEquals(productFormDto.getProductSellStatus(), product.getProductSellStatus());
        assertEquals(productFormDto.getProductDetail(), product.getProductDetail());
        assertEquals(productFormDto.getProductPrice(), product.getProductPrice());
        assertEquals(productFormDto.getProductQuantity(), product.getProductQuantity());
        assertEquals(multipartFileList.get(0).getOriginalFilename(),
                productImgList.get(0).getProductImgOriName());
    }
}