package com.bbmore.order.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_order_detail")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_code")
    private int orderDetailCode;

    @Column(name = "order_detail_price")
    private int orderDetailPrice;

    @Column(name = "order_detail_quantity")
    private int orderDetailQuantity;

    @ManyToOne
    @JoinColumn(name = "order_code", referencedColumnName = "order_code")
    private Order order; // Order 테이블과 관계 설정

    // Getters and setters

}