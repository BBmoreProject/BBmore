package com.bbmore.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class UserCartDTO {

    private Integer cartCode;              // 장바구니 고유번호
    private Integer cartProductQuantity;  // 장바구니 상품 수량
    private Integer userCode;             // FK 회원 고유번호
    private Integer productCode;          // FK 상품 고유번호

}
