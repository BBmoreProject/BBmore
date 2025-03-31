package com.bbmore.product.entity;

import com.bbmore.product.constant.ProductSellStatus;
import com.bbmore.product.repository.MemberRepository;
import com.bbmore.product.repository.OrderProductRepository;
import com.bbmore.product.repository.OrderRepository;
import com.bbmore.product.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

/**
 * 데이터베이스는 인메모리 H2 데이터베이스를 사용하고 있으며, 테스트 시작 시
 * 필요한 테이블들(cart, cart_product, member, order_product, orders, tbl_product 등)을 생성합니다.
 */
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@Transactional ///  테스트 끝나면 롤벡, 영속성 컨텍스트 활성화
class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @PersistenceContext
    EntityManager em;
    @Autowired
    private MemberRepository memberRepository;

    public Product createProduct() {
        Product product = new Product();
        product.setProductName("Product");
        product.setProductPrice(10000);
        product.setProductDetail("Product Detail");
        product.setProductSellStatus(ProductSellStatus.SELL);
        product.setProductStock(100);
        product.setProductRegTime(LocalDateTime.now());
        product.setProductUpdateTime(LocalDateTime.now());
        return product;
    }

    @Autowired
    OrderProductRepository orderItemRepository;

    @Test
    @DisplayName("LAZY LOADING TEST")
    public void LazyLoadingTest(){

        Order order = this.createOrder();
        Long orderProductId = order.getOrderProducts().get(0).getId();
        em.flush(); /// 변경사항 데이터베이스에 반영
        em.clear(); /// 영속성 컨텍스트 비우기 (모든 영속 객체들이 분리되어 조회 시 DB 리로딩)

        OrderProduct orderProduct = orderItemRepository.findById(orderProductId)
                .orElseThrow(EntityNotFoundException::new);
        System.out.println("Order Class : " + orderProduct.getOrder().getClass());
    }




    /**
     * 양방향 매핑관계 스스로 주입
     */

    @Test
    @DisplayName("영속성 전이 테스트")
    void cascadeTest(){

        Order order = new Order(); /// 새 주문 생성

        for(int i = 0; i < 10; i++){
            Product product = this.createProduct(); /// 상품을 생성하고 DB에 저장
            productRepository.save(product);
            OrderProduct orderProduct = new OrderProduct(); /// 주문상품 객체(주문과 상품을 연결하는 엔티티)
            orderProduct.setProduct(product); /// 어떤 상품인지
            orderProduct.setCount(10); /// 각 상품의 수량 10개
            orderProduct.setOrderPrice(1000); /// 주문 가격
            orderProduct.setOrder(order); /// 주문상품이 어떤 주문에 속하는지 설정 (다대일 관계)
            order.getOrderProducts().add(orderProduct); /// 주문에 이 주문상품을 추가 (일대다 관계)

        ///  ORDER 객체에  OrderProducts ArrayList 꺼내옴
        ///  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)의 cascade 설정 때문에 order가
        ///    저장될 때 orderProduct도 함께 저장
        }
        orderRepository.saveAndFlush(order);
        em.clear();

        Order savedOrder = orderRepository.findById(order.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(10, savedOrder.getOrderProducts().size());
    }

    public Order createOrder() {
        Order order = new Order();
        for(int i = 0; i < 5; i++){
            Product product = this.createProduct();
            productRepository.save(product);
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(product);
            orderProduct.setCount(5);
            orderProduct.setOrderPrice(1000);
            orderProduct.setOrder(order);
            /**
             * orderProduct.getOrder()처럼 실제로 Order 정보가 필요한 시점에 별도 쿼리가 발생합니다.
             */
            order.getOrderProducts().add(orderProduct);
        }

        Member member = new Member();
        memberRepository.save(member);

        order.setMember(member);
        orderRepository.save(order);
        return order;
    }

    /**
     * 생성 시점: JPA가 지연 로딩 관계의 엔티티를 조회할 때, 실제 엔티티 대신 프록시 객체를 생성하여 반환합니다.
     * 초기 상태: 프록시 객체는 처음에는 ID 값만 가지고 있고, 나머지 필드는 비어있습니다.
     * 초기화 시점: 프록시 객체의 실제 데이터가 필요한 메서드가 호출될 때(예: getter), 프록시 객체는 데이터베이스에 쿼리를 보내 실제 데이터를 로딩합니다. 이를 '프록시 초기화'라고 합니다.
     * 초기화 후: 한번 초기화된 프록시는 이후에 다시 데이터베이스에 접근하지 않고 메모리에 캐시된 데이터를 사용합니다.
     */

    @Test
    @DisplayName("고아객체 제거 테스트")
    public void orphanRemovalTest(){
        Order order = this.createOrder();
        order.getOrderProducts().remove(0);
        em.flush();
        em.clear();

    }


}