package com.bbmore.product.dto;

import com.bbmore.member.entity.Member;
import com.bbmore.order.entity.OrderDetail;
import com.bbmore.product.entity.Review;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewWritingDTO {

    private Integer reviewCode; // 수정, 삭제 시 필요
    private Integer userCode;   // 저장 시 필요
    private Integer orderDetailCode;    // 저장 시 필요

    private String orderStatus;
    private String productName;
//    private String productImg;
    private Integer orderDetailPrice;
    private Integer orderDetailQuantity;

    private Integer reviewRating;

    private String reviewContent;

    private LocalDate reviewDate; // 수정 시 필요 (작성된 리뷰 페이지로 값을 전달해줘야함)


    // 리뷰 작성할 제품 목록 조회 시 사용
    public ReviewWritingDTO(String orderStatus, String productName,
                            Integer orderDetailPrice,
                            Integer orderDetailQuantity) {
        this.orderStatus = orderStatus;
        this.productName = productName;
//        this.productImg = productImg;
        this.orderDetailPrice = orderDetailPrice;
        this.orderDetailQuantity = orderDetailQuantity;
    }


    // 특정 주문 상세의 리뷰 정보 조회
    public ReviewWritingDTO(Integer reviewCode,Integer reviewRating, String reviewContent,
                            String orderStatus, String productName,
                            Integer orderDetailPrice, Integer orderDetailQuantity) {
        this.reviewCode = reviewCode;
        this.reviewRating = reviewRating;
        this.reviewContent = reviewContent;
        this.orderStatus = orderStatus;
        this.productName = productName;
//        this.productImg = productImg;
        this.orderDetailPrice = orderDetailPrice;
        this.orderDetailQuantity = orderDetailQuantity;
    }


    public Review toEntity(Member member, OrderDetail orderDetail) {
        return Review.builder()
                .reviewCode(this.reviewCode)
                .reviewRating(this.reviewRating)
                .reviewContent(this.reviewContent)
                .reviewDate(this.reviewDate)
                .member(member)
                .orderDetail(orderDetail)
                .build();
    }
}
