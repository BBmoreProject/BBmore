package com.bbmore.product.dto;

import com.bbmore.product.constant.OrderStatus;
import com.bbmore.product.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderHistDTO {

    public OrderHistDTO(Order order) {
        this.orderId = order.getId();
        this.orderDate =
                order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();

    }

    private Long orderId;

    private String orderDate;

    private OrderStatus orderStatus;

    private List<OrderProductDTO> orderProductDTOList = new ArrayList<>();

    public void addOrderProductDTO(OrderProductDTO orderProductDTO) {
        orderProductDTOList.add(orderProductDTO);
    }
}
