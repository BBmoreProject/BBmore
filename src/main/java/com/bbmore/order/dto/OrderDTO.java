package com.bbmore.order.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
public class OrderDTO {
    private int orderCode;
    private int orderTotalPrice;
    private LocalDate orderDate;
    private String orderStatus;
    private String recipientAddress;
    private String recipientPhone;
    private String orderDeliveryRequest;
    private String productName;
    private int productQuantity;

    private int userCode;
    private int orderDetailCode;

    private MemberDTO member;


}
