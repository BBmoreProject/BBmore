package com.bbmore.order.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_order")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderCode;
    private int orderTotalPrice;
    private String orderDate;
    private String orderStatus;
    private String recipientName;
    private String recipientAddress;
    private int recipientPhone;
    private String orderDeliveryRequest;
    private String productName;
    private int productQuantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCode")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderDetailCode") // OrderDetail의 orderDetailCode와 연결
    private OrderDetail orderDetail;
}
