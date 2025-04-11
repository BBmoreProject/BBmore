package com.bbmore.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ReviewWriteDTO {

    private LocalDate orderDate;
    private String orderStatus;
    private String productName;
//    private String productImg;
    private Integer orderDetailCode; // 특정 주문 상세를 가져와서 리뷰 작성하기를 위함
    private Integer orderDetailPrice;
    private Integer orderDetailQuantity;
}
