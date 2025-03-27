package com.bbmore.order.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
public class OrderDTO {
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
