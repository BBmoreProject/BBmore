package com.bbmore.product.repository;

import com.bbmore.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository
<Product, Integer>, QuerydslPredicateExecutor<Product> {


    Product findByProductCode(Integer productCode);
}
