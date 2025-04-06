//package com.bbmore.member.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//
//@Entity
//@Getter
//@Table(name = "tbl_order_detail")
//public class OrderDetail {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "order_detail_code")
//    private Integer orderDetailCode;
//
//    @Column(name = "order_detail_price", nullable = false)
//    private Integer orderDetailPrice;
//
//    @Column(name = "order_detail_quantity", nullable = false)
//    private Integer orderDetailQuantity;
//
//    // FK
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_code")
//    private Order order;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_code")
//    private Product product;
//
//
//}
