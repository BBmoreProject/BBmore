package com.bbmore.product.repository;

import com.bbmore.product.constant.ProductSellStatus;
import com.bbmore.product.entity.Product;
import com.bbmore.product.entity.QProduct;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.yml")
class productRepositoryTest {



    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("Product Storage Test")
    public void createProductTest() {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductDetail("Test Product Detail");
        product.setProductPrice(1000);
        product.setProductStock(100);
        product.setProductUpdateTime(LocalDateTime.now());
        product.setProductCreateTime(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        System.out.println(savedProduct);
    }

    public void createProductList() {
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setProductName("Test Product" + i);
            product.setProductDetail("Test Product Detail" + i);
            product.setProductPrice(1000 + i);
            product.setProductStock(100);
            product.setProductUpdateTime(LocalDateTime.now());
            product.setProductCreateTime(LocalDateTime.now());
            productRepository.save(product);
        }
    }

    @Test
    @DisplayName("productName Search Test")
    public void findByProductNameTest() {
        this.createProductList();
        List<Product> productList = productRepository.findByProductName("Test Product1");
        for(Product product : productList) {
            System.out.println(product.toString());
        }
    }


    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("Querydsl Test")
    public void querydslTest() {
        this.createProductList();
        ///  QueryDsl 사용하기 위한 JPAQueryFactory 생성
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        ///  Querydsl 자동으로 생성한 QProduct 클래스의 정적 인스턴스를 참조.
        QProduct qProduct = QProduct.product;
        JPAQuery<Product> query = queryFactory.selectFrom(qProduct)
                .where(qProduct.productSellStatus.eq(ProductSellStatus.SELL))
                .where(qProduct.productDetail.like("%" + "Product Detail" + "%"))
                .orderBy(qProduct.productPrice.asc());

        List<Product> productList = query.fetch();

        for(Product product : productList) {
            System.out.println(product.toString());
        }
    }



}