package com.bbmore.order.entity;

import com.bbmore.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Table(name = "tbl_order_detail")
public class OrderDetail {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_code", nullable = false)
    private Integer orderDetailCode;

    @Column(name = "order_detail_price", nullable = false)
    private Integer orderDetailPrice;    // 총 결제금액

    @Column(name = "order_detail_quantity", nullable = false)
    private LocalDate orderDetailQuantity;     // 주문 날짜


    // FK(반품고유번호)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "return_code", nullable = false)
    private UserReturn userReturn;

    // FK(교환고유번호)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_code", nullable = false)
    private UserExchange userExchange;

    // FK(주문고유번호)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_code", nullable = false)
    private Order order;

    // FK(상품고유번호)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", nullable = false)
    private Product product;


}
