package com.bbmore.order.dto;

import lombok.*;


import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Integer orderCode;              // 주문고유번호

    private Integer orderTotalPrice;        // 총결제금액

    private LocalDate orderDate;           // 주문날짜

    private String orderStatus;            // 주문현황

    private String recipientName;          // 받는분 성함

    private String recipientAddress;        // 받는분 주소

    private String recipientPhone;          // 받는분 전화번호

    private String orderDeliveryRequest;    // 배송요청사항


    private Integer userCode;               // fk 회원고유번호


}
