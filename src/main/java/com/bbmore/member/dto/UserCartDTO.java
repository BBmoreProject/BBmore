package com.bbmore.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class UserCartDTO {

    private int cartCode;  // 장바구니 고유번호
    private int cartProductQuantity;  // 장바구니 상품 수량
    private int userCode;  // 회원 고유번호
    private int productCode;  // 상품 고유번호

}
