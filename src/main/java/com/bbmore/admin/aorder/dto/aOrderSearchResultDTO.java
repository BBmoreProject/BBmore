package com.bbmore.admin.aorder.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class aOrderSearchResultDTO {

    private Integer orderCode;         // 주문번호
    private LocalDate orderDate;       // 주문날짜

    private String productName;        // 상품 이름

    private String userName;           // 회원 이름
    private String userPhoneNumber;    // 전화번호
    private String userAddress;
}
