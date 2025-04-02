package com.bbmore.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseListDTO {

    private Integer orderTotalPrice;  // 총 결제금액
    private Date orderDate;     // 주문 날짜
    private String orderStatus;    // 배송 현황
    private String productName;   // 상품명
    private String productImg;    // 상품 이미지
    private Integer orderDetailPrice; // 주문 상세 가격
    private Integer orderDetailQuantity; // 주문 상품 개수
}
