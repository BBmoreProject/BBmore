package com.bbmore.product.repository;

import com.bbmore.product.entity.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImgRepository extends JpaRepository<ProductImg, Integer> {

    /// 적으면서도 너무 긴거같은데...
    List<ProductImg> findByProductProductCodeOrderByProductImgCodeAsc(Integer productCode);

    ProductImg findByProductProductCodeAndProductImgRepYn
            (Integer productCode, String productImgRepYn);
}
