package com.bbmore.member.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_code", nullable = false)
    private Integer orderCode;

    @Column(name = "order_total_price", nullable = false)
    private Integer orderTotalPrice;    // 총 결제금액

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;     // 주문 날짜

    @Column(name = "order_status", nullable = false)
    private String orderStatus; // 주문 현황

    @Column(name = "recipient_name", nullable = false)
    private String recipientName;

    @Column(name = "recipient_address", nullable = false)
    private String recipientAddress;    // 받는분 주소

    @Column(name = "recipient_phone", nullable = false)
    private String recipientPhone;  // 받는분 전화번호

    @Column(name = "order_delivery_request", nullable = false)
    private String orderDeliverYRequest; // 배송요청사항


    // FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code", nullable = false)
    private Member member;



}
