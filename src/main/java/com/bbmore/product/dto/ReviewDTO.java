package com.bbmore.product.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {

    private Integer reviewCode;              // 리뷰고유번호

    private Integer reviewRating;            // 리뷰별점

    private LocalDate reviewDate;            // 리뷰작성일

    private String reviewContent;            // 리뷰내용


    private Integer userCode;                 // fk 회원고유번호

    private Integer orderDetailCode;          // fk 주문상세고유번호

}
