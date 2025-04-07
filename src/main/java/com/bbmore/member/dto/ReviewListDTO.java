package com.bbmore.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ReviewListDTO {

    private Integer reviewCode; // 삭제 기능을 위해 추가

    private Integer orderDetailCode;

    private String orderStatus;
    private String productName;
//    private String productImg;
    private Integer orderDetailPrice;
    private Integer orderDetailQuantity;
    private Integer reviewRating;
    private LocalDate reviewDate;




    public ReviewListDTO(String orderStatus,
                         String productName,
                         Integer orderDetailPrice, Integer orderDetailQuantity,
                         Integer reviewRating, LocalDate reviewDate) {
        this.orderStatus = orderStatus;
        this.productName = productName;
//        this.productImg = productImg;
        this.orderDetailPrice = orderDetailPrice;
        this.orderDetailQuantity = orderDetailQuantity;
        this.reviewRating = reviewRating;
        this.reviewDate = reviewDate;
    }


}
