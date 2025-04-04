package com.bbmore.order.entity;

import com.bbmore.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_order")
@Getter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_code", nullable = false)
    private Integer orderCode;

    @Column(name = "order_total_price", nullable = false)
    private Integer orderTotalPrice;    // 총 결제금액

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;     // 주문 날짜     // Date -> LocalDate  04.04 태민변경

    @Column(name = "order_status", nullable = false)
    private String orderStatus; // 주문 현황

    @Column(name = "recipient_address", nullable = false)
    private String recipientAddress;    // 받는분 주소

    @Column(name = "recipient_phone", nullable = false)
    private String recipientPhone;  // 받는분 전화번호

    @Column(name = "order_delivery_request", nullable = false)
    private String orderDeliverYRequest; // 배송요청사항

    @Column(name = "product_name", nullable = false)
    private String productName; // 제품명

    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity;    // 주문 상품 개수

    // FK

    @ManyToOne(fetch = FetchType.LAZY)
    // 04.04 11:37 referencedColumnName = "user_code" 추가 -태민-
    @JoinColumn(name = "user_code", nullable = false, referencedColumnName = "user_code")
    private Member member;


    // 04.04 11:37 추가 -태민-
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails; // OrderDetail 테이블과 관계 설정






}
