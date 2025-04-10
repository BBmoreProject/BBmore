package com.bbmore.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseListDTO {

    private Integer orderTotalPrice;  // 총 결제금액
    private LocalDate orderDate;     // 주문 날짜
    private String orderStatus;    // 배송 현황
    private String productName;   // 상품명
    private Integer orderDetailPrice; // 주문 상세 가격
    private Integer orderDetailQuantity; // 주문 상품 개수

    private Integer orderDetailCode;
    private boolean reviewWritten; // 리뷰 작성 여부 확인
}
