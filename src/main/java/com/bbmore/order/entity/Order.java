package com.bbmore.order.entity;

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
    @Column(name = "order_code")
    private int orderCode;

    @Column(name = "order_total_price")
    private int orderTotalPrice;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "recipient_address")
    private String recipientAddress;

    @Column(name = "recipient_phone")
    private String recipientPhone;

    @Column(name = "order_delivery_request")
    private String orderDeliveryRequest;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_quantity")
    private int productQuantity;

    @ManyToOne
    @JoinColumn(name = "user_code", referencedColumnName = "user_code")
    private Member member; // User 테이블과 관계 설정

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails; // OrderDetail 테이블과 관계 설정


    // Getters and setters
}
