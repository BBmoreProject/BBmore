package com.bbmore.product.dto;

import com.bbmore.product.entity.OrderProduct;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductDTO {

    public OrderProductDTO(OrderProduct orderProduct, String imgUrl) {
        this.productName = orderProduct.getProduct().getProductName();
        this.count = orderProduct.getCount();
        this.orderPrice = orderProduct.getOrderPrice();
        this.imgUrl = imgUrl;
    }

    private String productName;

    private int count;

    private int orderPrice;

    private String imgUrl;

}
