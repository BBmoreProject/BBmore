package com.bbmore.order.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    private LocalDate orderDate;
    private String orderStatus;
    private String recipientName;
    private String recipientAddress;
    private int recipientPhone;
    private String orderDeliveryRequest;
    private String productName;
    private int productQuantity;

    private int userCode;
    private int orderDetailCode;

}
