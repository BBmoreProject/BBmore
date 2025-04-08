package com.bbmore.order.dto;

import com.bbmore.order.entity.OrderDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {

    public OrderItemDto (OrderDetail orderDetail, String imgUrl) {
        this.productName = orderDetail.getProduct().getProductName();
        this.count = orderDetail.getOrderDetailQuantity();
        this.orderPrice = orderDetail.getOrderDetailPrice();
        this.imgUrl = imgUrl;
    }

    private String productName;

    private int count;

    private int orderPrice;

    private String imgUrl;
}
