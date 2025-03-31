package com.bbmore.product.repository;

import com.bbmore.product.entity.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImgRepository extends JpaRepository<ProductImg, Integer> {


    List<ProductImg> findByProductProductIdOrderByIdAsc(Long Id);

}
