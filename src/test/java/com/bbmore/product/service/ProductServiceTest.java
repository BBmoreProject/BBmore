package com.bbmore.product.service;

import com.bbmore.product.constant.ProductSellStatus;
import com.bbmore.product.dto.ProductFormDTO;
import com.bbmore.product.entity.Product;
import com.bbmore.product.entity.ProductImg;
import com.bbmore.product.repository.ProductImgRepository;
import com.bbmore.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
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

    List<MultipartFile> createMultipartFiles() throws Exception{
        List<MultipartFile> multipartFileList = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            String path = "file:///src/main/resources/static/";
            String imageName = "product" + i + ".jpg";
            MultipartFile multipartFile =
                    new MockMultipartFile(path, imageName,
                            "image/jpg", new byte[]{1,2,3,4});

            multipartFileList.add(multipartFile);
        }
        return multipartFileList;
    }

    @Test
    @DisplayName("상품 등록 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void saveItem() throws Exception{
        ProductFormDTO productFormDTO = new ProductFormDTO();
        productFormDTO.setProductName("테스트상품");
        productFormDTO.setProductSellStatus(ProductSellStatus.SELL);
        productFormDTO.setProductDetail("테스트 상품입니다");
        productFormDTO.setPrice(1000);
        productFormDTO.setStockNumber(100);


        List<MultipartFile> multipartFileList = createMultipartFiles();
        Long productId = productService.saveProduct(productFormDTO, multipartFileList);

        List<ProductImg> productImgList =
                productImgRepository.findByProductProductIdOrderByIdAsc(productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(product.getProductName(), productFormDTO.getProductName());
        assertEquals(product.getProductSellStatus(), productFormDTO.getProductSellStatus());
        assertEquals(product.getProductDetail(), productFormDTO.getProductDetail());
        assertEquals(product.getProductPrice(), productFormDTO.getPrice());
        assertEquals(product.getProductStock(), productFormDTO.getStockNumber());
        assertEquals(multipartFileList.get(0).getOriginalFilename(),
                productImgList.get(0).getProductOriginalImgName());


    }

}