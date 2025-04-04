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
    private Integer orderDetailCode;
    private Integer orderDetailPrice;
    private Integer orderDetailQuantity;
    private Integer orderCode;
}