package com.bbmore.member.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserCartDTO {

    private Integer cartCode;              // 리뷰고유번호

    private Integer cartProductQuantity;   // 리뷰별점


    private Integer userCode;             // fk 회원고유번호

    private Integer productCode;          // fk 상품고유번호

}