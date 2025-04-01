package com.bbmore.product.repository;

import com.bbmore.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,
        QuerydslPredicateExecutor<Product>, ProductRepositoryCustom {

    List<Product> findByProductName(String productName);

    List<Product> findByProductNameOrProductDetail(String productName, String productDetail);

    List<Product> findByProductPriceLessThan(Integer productPrice); /// Integer 대신 int 사용하면 null 시 에러





}
