package com.bbmore.product.repository;

import com.bbmore.product.entity.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {





    List<ProductImg> findByProductIdOrderByIdAsc(Long id);

    ProductImg findByProductIdAndRepresentativeImg(Long id, String representativeImg);



    // 상품 ID로 이미지 리스트 조회



}
