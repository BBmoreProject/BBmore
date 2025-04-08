package com.bbmore.order.entity;

import com.bbmore.order.constant.OrderStatus;
import com.bbmore.product.config.BaseTimeEntity;
import com.bbmore.product.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tbl_order")
public class Order extends BaseTimeEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_code")
    private Integer orderCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code")
    private Member member;

    @Column(name = "order_date")
    private LocalDate orderDate;     // 주문 날짜

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문 현황

    @Column(name = "recipient_address")
    private String recipientAddress;    // 받는분 주소

    @Column(name = "recipient_phone")
    private String recipientPhone;  // 받는분 전화번호

    @Column(name = "order_delivery_request")
    private String orderDeliverYRequest; // 배송요청사항

    /// 한 개의 주문이 여러 개의 주문 상품을 가짐.
    /// 주문을 저장하면 주문 상품 엔티티도 함께 저장함.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,
    orphanRemoval = true, fetch = FetchType.LAZY) ///  고아 객체 제거
    private List<OrderDetail> orderDetailList = new ArrayList<>();

///  양방향 관계 매핑
    public void addOrderProduct(OrderDetail orderDetail) {
        orderDetailList.add(orderDetail);
        orderDetail.setOrder(this);
    }

    public static Order createOrder(Member member, List<OrderDetail> orderDetailList) {

        Order order = new Order();
        order.setMember(member);
        for(OrderDetail orderDetail : orderDetailList) {
            order.addOrderProduct(orderDetail);
        }
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDate.now());
        return order;
    }

    public int getTotalPrice() {
        int totalprice = 0;
        for(OrderDetail orderDetail : orderDetailList) {
            totalprice += orderDetail.getTotalPrice();
        }
        return totalprice;
    }


    ///  사용하지 않음

//    @Column(name = "product_name", nullable = false)
//    private String productName; // 제품명
//
//    @Column(name = "product_quantity", nullable = false)
//    private Integer productQuantity;    // 주문 상품 개수
//
//
//    @Column(name = "order_total_price", nullable = false)
//    private Integer orderTotalPrice;    // 총 결제금액
//



}
