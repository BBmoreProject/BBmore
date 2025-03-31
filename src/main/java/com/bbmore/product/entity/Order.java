package com.bbmore.product.entity;

import com.bbmore.product.config.BaseEntity;
import com.bbmore.product.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter

public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") ///  하나의 회원은 여러 개의 주문을 가질 수 있음
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    /**

     * 영속성 전이(Cascade) 설정:
     *  Order 클래스는 여러 개의 OrderProduct를 가질 수 있습니다.
     *  OrderProduct 클래스의 "order" 필드가 이 관계의 주인입니다 (mappedBy="order").
     *  OrderProduct 테이블에 order_id 외래 키 컬럼이 생성됩니다.
     *
    Order 객체를 저장하면 → 연결된 모든 OrderProduct도 자동 저장
    Order 객체를 업데이트하면 → 연결된 모든 OrderProduct도 자동 업데이트
    Order 객체를 삭제하면 → 연결된 모든 OrderProduct도 자동 삭제
    즉, Order에 가한 모든 DB 작업이 OrderProduct에도 자동 적용됨
     *      */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    private LocalDateTime regTime;

    private LocalDateTime updateTime;


}
