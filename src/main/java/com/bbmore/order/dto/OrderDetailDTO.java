package com.bbmore.order.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailDTO {

    private Integer orderDetailCode;        // 주문상세고유번호

    private Integer orderDetailPrice;       // 주문상세가격

    private Integer orderDetailQuantity;    // 주문상세개수


    private Integer returnCode;             // fk 반품고유번호
    private Integer exchangeCode;           // fk 교환고유번호
    private Integer orderCode;              // fk 주문고유번호
    private Integer productCode;            // fk 상품고유번호


}
