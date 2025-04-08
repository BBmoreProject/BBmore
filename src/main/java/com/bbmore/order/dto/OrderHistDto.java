package com.bbmore.order.dto;

import com.bbmore.order.constant.OrderStatus;
import com.bbmore.order.entity.OrderDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderHistDto {

    public OrderHistDto(){}

    private Integer orderCode;

    private String orderDate;

    private OrderStatus orderStatus;

    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    public void addOrderProduct(OrderItemDto orderItemDto) {
        orderItemDtoList.add(orderItemDto);
    }
}
