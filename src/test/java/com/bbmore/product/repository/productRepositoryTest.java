//package com.bbmore.product.repository;
//
//import com.bbmore.product.constant.ProductSellStatus;
//import com.bbmore.product.entity.Product;
//import com.bbmore.product.entity.QProduct;
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.jpa.impl.JPAQuery;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.TestPropertySource;
//import org.thymeleaf.util.StringUtils;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestPropertySource(locations="classpath:application-test.yml")
//class productRepositoryTest {
//
//
//
//    @Autowired
//    ProductRepository productRepository;
//
//    @Test
//    @DisplayName("Product Storage Test")
//    public void createProductTest() {
//        Product product = new Product();
//        product.setProductName("Test Product");
//        product.setProductDetail("Test Product Detail");
//        product.setProductPrice(1000);
//        product.setProductStock(100);
//        product.setProductUpdateTime(LocalDateTime.now());
//        product.setProductRegTime(LocalDateTime.now());
//
//        Product savedProduct = productRepository.save(product);
//        System.out.println(savedProduct);
//    }
//
//    public void createProductList() {
//        for (int i = 0; i < 10; i++) {
//            Product product = new Product();
//            product.setProductName("Test Product" + i);
//            product.setProductDetail("Test Product Detail" + i);
//            product.setProductPrice(1000 + i);
//            product.setProductStock(100);
//            product.setProductUpdateTime(LocalDateTime.now());
//            product.setProductRegTime(LocalDateTime.now());
//            productRepository.save(product);
//        }
//    }
//
//    @Test
//    @DisplayName("productName Search Test")
//    public void findByProductNameTest() {
//        this.createProductList();
//        List<Product> productList = productRepository.findByProductName("Test Product1");
//        for(Product product : productList) {
//            System.out.println(product.toString());
//        }
//    }
//
//
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @Test
//    @DisplayName("Querydsl Test")
//    public void querydslTest() {
//        this.createProductList();
//        ///  QueryDsl 사용하기 위한 JPAQueryFactory 생성
//        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
//        ///  Querydsl 자동으로 생성한 QProduct 클래스의 정적 인스턴스를 참조.
//        QProduct qProduct = QProduct.product;
//        /**
//         * query 변수에는 실행 준비가 된 QueryDsl의 쿼리 객체가 담긴다.
//         * 구체적으로 JPAQuery<Product> 타입의 객체가 담긴다.
//          */
//
//        JPAQuery<Product> query =
//                queryFactory.selectFrom(qProduct)
//                .where(qProduct.productSellStatus.eq(ProductSellStatus.SELL))
//                .where(qProduct.productDetail.like("%" + "Product Detail" + "%"))
//                .orderBy(qProduct.productPrice.asc());
//
//        /// query.fecth() 메서드 호출 시 : 실제 데이터베이스 쿼리 실행 -> QueryDsl이 내부적으로 JPQL 쿼리 생성(EntityManager을 통해)
//        /// 데이터베이스로 받은 결과를 Java 객체 (Product)로 변환하여 반환
//
//        List<Product> productList = query.fetch();
//
//        ///  이러한 product 객체를 List<Product> 객체에 담아 반환
//
//        for(Product product : productList) {
//            System.out.println(product.toString());
//        }
//    }
//
//    public void createItemList2() {
//        for(int i = 0; i < 10; i++) {
//            Product product = new Product();
//            product.setProductName("Test Product" + i);
//            product.setProductDetail("Test Product Detail" + i);
//            product.setProductPrice(1000 + i);
//            product.setProductStock(100);
//            product.setProductSellStatus(ProductSellStatus.SELL);
//            product.setProductUpdateTime(LocalDateTime.now());
//            product.setProductRegTime(LocalDateTime.now());
//            productRepository.save(product);
//        }
//        for(int i = 0; i < 10; i++) {
//            Product product = new Product();
//            product.setProductName("Test Product" + i);
//            product.setProductDetail("Test Product Detail" + i);
//            product.setProductPrice(1000 + i);
//            product.setProductStock(100);
//            product.setProductSellStatus(ProductSellStatus.SOLD_OUT);
//            product.setProductUpdateTime(LocalDateTime.now());
//            product.setProductRegTime(LocalDateTime.now());
//            productRepository.save(product);
//        }
//    }
//
//    @Test
//    @DisplayName("Querydsl Test2")
//    public void queryDslTest2() {
//
//        this.createItemList2();
//
//        BooleanBuilder booleanBuilder = new BooleanBuilder();
//        QProduct product = QProduct.product;
//        String productDetail = "Test Product Detail";
//        int productPrice = 1003;
//        String productSellStat = "SELL";
//
//        ///  BooleanBuilder 객체로 여러 조건을 논리적으로 결합하는 동적 쿼리 작성
//        ///  WHERE product_detail LIKE '%{productDetail}%
//        ///  AND product_price > {productPrice}
//        booleanBuilder.and(product.productDetail.like("%" + productDetail + "%"));
//        booleanBuilder.and(product.productPrice.gt(productPrice));
//
//        ///  productSellStat 값이 null이 아닌 경우에만 조건 추가 (값이 있는 경우에만?)
//        ///  WHERE product_sell_status = 'SELL'과 같음 (AND 비교)
//        if(StringUtils.equals(productSellStat, ProductSellStatus.SELL)){
//            booleanBuilder.and(product.productSellStatus.eq(ProductSellStatus.SELL));
//        }
//
//        ///  Pageable 인터페이스는 페이징 정보를 담는 객체 0, 5는 0부터 5까지 데이터를 보여주겠다
//        ///  BooleanBuilder에는 조건이 순차적으로 쌓아감
//        /**
//         * 조건들 = [
//         *   "상품 설명에 특정 단어 포함",
//         *   "가격이 특정 값보다 큼",
//         *   "판매 상태가 SELL임" (조건부로 추가)
//         * ]
//         */
//
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<Product> productPagingResult =
//                /// booleanBuilder에 담긴 값 && 페이징 처리 된 표시할 여부
//                productRepository.findAll(booleanBuilder, pageable);
//        System.out.println("total elements : " + productPagingResult.getTotalElements());
//        ///  getTotalElements() = Page 인터페이스에서 제공하는 메서드로 전체 데이터의 갯수 반환
//
//        List<Product> resultProductList = productPagingResult.getContent();
//        /// getContent()를 사용하지 않는다면 productPagingResult는 Page<Product> 타입으로 for 루프에서 사용할 수 없음
//        for(Product resultProduct : resultProductList) {
//            ///  getContent(): 페이지에 포함된 실제 데이터 목록을 가져옴
//        ///  페이징 결과에서 페이지 번호, 전체 페이지 수 등 메타데이터 없이 순수한 데이터 리스트 필요할 때
//            System.out.println(resultProduct.toString());
//
//            /**
//             *     where
//             *         p1_0.product_detail like ? escape '!'
//             *         and p1_0.product_price>?
//             *         and p1_0.product_sell_status=?
//             *
//             *         !의 의미?
//             *         escape '!' -> 특수문자를 문자열로 인식하게 함 (t%shirt- %를 일반 문자열 "%"로 검색)
//             */
//        }
//
//
//
//
//
//    }
//
//
//
//}