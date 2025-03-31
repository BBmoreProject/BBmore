package com.bbmore.product.service;

import com.bbmore.product.entity.ProductImg;
import com.bbmore.product.repository.ProductImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductImgService {

    @Value("${productImgLocation}") ///  productImgLocation 속성 값을 주입
    private String productImgLocation;

    private final ProductImgRepository productImgRepository;

    private final FileService fileService; /// 파일 처리를 위한 서비스를 의존성 주입받을 필드입니다.

    ///  상품 이미지를 저장하는 메소드로, ProductImg 엔티티와 업로드된 파일을 매개변수로 받습니다.
    public void saveProductImg(ProductImg productImg,
                               MultipartFile productImgFile) throws Exception{

        ///  String originalFilename = productImgFile.getOriginalFilename();
        String originalFilename = productImgFile.getOriginalFilename();
        ///  서버에 저장될 이미지 파일명을 위한 변수를 초기화합니다.
        String productImgName = "";
        String productImgUrl = "";

        ///  원본 파일명이 비어있지 않은 경우에만 다음 작업을 수행합니다
        if(!StringUtils.isEmpty(originalFilename)){
            productImgName = fileService.uploadFile(productImgLocation,
                    originalFilename, productImgFile.getBytes());

            ///  FileService를 통해 파일을 업로드하고, 저장된 파일명을 반환받습니다.

        /// 웹에서 접근할 수 있는 이미지 URL을 생성합니다.
            productImgUrl = "/images/products/" + productImgName;
        }

        ///  ProductImg 객체에 파일명과 URL 정보를 업데이트합니다.
        productImg.updateProductImg(originalFilename, productImgName, productImgUrl);
        productImgRepository.save(productImg);
    }
}
