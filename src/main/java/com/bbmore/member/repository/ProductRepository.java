package com.bbmore.member.repository;

import com.bbmore.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  // 상품 코드로 상품 조회
  Product findByProductCode(Integer productCode);

}
