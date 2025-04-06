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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createProductTest() {
        Product product = new Product();
        product.setProductName("테스트 상품");
        product.setProductPrice(10000);
        product.setProductDetail("테스트 상품 상세 설명");
        product.setProductSellStatus(ProductSellStatus.SELL);
        product.setProductQuantity(100);
        product.setRegTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        Product saveProduct = productRepository.save(product);
        System.out.println(saveProduct.toString());
    }

    public void createProductList() {
        for(int i = 1; i <= 10; i++) {
            Product product = new Product();
            product.setProductName("테스트 상품 " + i);
            product.setProductPrice(10000 + i);
            product.setProductDetail("테스트 상품 상세 설명" + i);
            product.setProductSellStatus(ProductSellStatus.SELL);
            product.setProductQuantity(100);
            product.setRegTime(LocalDateTime.now());
            Product saveProduct = productRepository.save(product);
        }
    }

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("Querydsl 1")
    public void querydslTest1() {
        this.createProductList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;
        JPAQuery<Product> query = queryFactory.selectFrom(qProduct)
                .where(qProduct.productSellStatus.eq(ProductSellStatus.SELL))
                .where(qProduct.productDetail.like("%" + "테스트 상품 상세 설명" + "%"))
                .orderBy(qProduct.productPrice.desc());

        List<Product> productList = query.fetch();
        for(Product product : productList) {
            System.out.println(product);
        }
    }
}