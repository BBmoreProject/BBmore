package com.bbmore.product.entity;

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

public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id") ///  하나의 회원은 여러 개의 주문을 가질 수 있음
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    /**
     * @mappedBy 속성의 역할은 양방향 연관 관계에서 연관 관계의 주인을 지정하
     * mappedBy가 지정된 쪽이 읽기 전용
     * 일대다(One-to-Many) 관계 설정:
     * 하나의 주문(Order)이 여러 주문 상품(OrderProduct)을 가질 수 있음을 정의합니다.
     * mappedBy = "order"는 이 관계가 OrderProduct 엔티티의 order 필드에 의해 관리됨을 의미합니다.

     * 양방향 관계 설정:
     * mappedBy 속성은 이 관계가 양방향이며, 관계의 주인(owner)은 OrderProduct 클래스의 order 필드임을 나타냅니다.
     * Order 엔티티는 연관관계의 주인이 아니므로 직접 외래키를 관리하지 않습니다.

     * 영속성 전이(Cascade) 설정:
     * cascade = CascadeType.ALL은 Order 엔티티에 대한 모든 영속성 작업(저장, 업데이트, 삭제 등)이 연관된 모든 OrderProduct 엔티티에도 자동으로 적용됨을 의미합니다.
     * 예를 들어, Order를 저장하면 연결된 모든 OrderProduct 항목도 자동으로 저장됩니다.
     * Order를 삭제하면 연결된 모든 OrderProduct 항목도 자동으로 삭제됩니다.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderItems = new ArrayList<>();

    private LocalDateTime regTime;

    private LocalDateTime updateTime;


}
