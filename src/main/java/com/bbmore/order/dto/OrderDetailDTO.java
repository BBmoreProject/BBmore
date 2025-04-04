package com.bbmore.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class OrderDetailDTO {
    private int orderDetailCode;
    private int orderDetailPrice;
    private int orderDetailQuantity;
    private int orderCode;
}