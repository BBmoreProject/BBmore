package com.bbmore.order.entity;

import com.bbmore.member.entity.Member;
import com.bbmore.product.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
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
    private String recipientName;    // 받는분 성함

    @Column(name = "recipient_address", nullable = false)
    private String recipientAddress;    // 받는분 주소

    @Column(name = "recipient_phone", nullable = false)
    private String recipientPhone;  // 받는분 전화번호

    @Column(name = "order_delivery_request")
    private String orderDeliverYRequest; // 배송요청사항

    @Column(name = "product_name", nullable = false)
    private String productName; // 제품명

    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity;    // 주문 상품 개수

    // FK(회원고유번호)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code", nullable = false)
    private Member member;



}
