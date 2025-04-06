package com.bbmore.product.service;

import com.bbmore.product.entity.ProductImg;
import com.bbmore.product.repository.ProductImgRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductImgService {

    private final ProductImgRepository productImgRepository;
    @Value("${productImgLocation}")
    private String productImgLocation;

    private final FileService fileService;

    /**
     * fileService 를 사용해 파일을 저장하고
     * 이를 통해 DB에 저장한다.
     * SRP - 관심분리 역할
     */
    public void saveProductImg(ProductImg productImg,
                               MultipartFile productImgFile)throws Exception {
        String productImgOriName = productImgFile.getOriginalFilename();
        String productImgName = "";
        String productImgUrl = "";

        if(!StringUtils.isEmpty(productImgOriName)) {
            productImgName = fileService.uploadFile(productImgLocation,
                    productImgOriName, productImgFile.getBytes());
            productImgUrl = "/images/product/" + productImgName;
        }

        productImg.updateProductImg(productImgOriName, productImgName, productImgUrl);
        productImgRepository.save(productImg);
    }

    public void updateProductImg(Integer productImgId, MultipartFile productImgFile)
        throws Exception {

        if(!productImgFile.isEmpty()) { /// 더티체킹을 감지해 상품 이미지 업데이트
            ProductImg savedProductImg = productImgRepository.findById(productImgId)
                    .orElseThrow(EntityNotFoundException::new);

            if(!StringUtils.isEmpty(savedProductImg.getProductImgName())) {
                fileService.deleteFile(productImgLocation + "/" +
                        savedProductImg.getProductImgName());
            }

            String productImgOriName = productImgFile.getOriginalFilename();
            String productImgName = fileService.uploadFile(productImgLocation,
                    productImgOriName, productImgFile.getBytes());
            String productImgUrl = "/images/product/" + productImgName;
            savedProductImg.updateProductImg(productImgOriName, productImgName, productImgUrl);

        }

    }

}
