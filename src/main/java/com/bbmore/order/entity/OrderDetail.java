package com.bbmore.order.entity;

import com.bbmore.order.entity.Order;
import com.bbmore.product.config.BaseEntity;
import com.bbmore.product.config.BaseTimeEntity;
import com.bbmore.product.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tbl_order_detail")
public class OrderDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_code")
    private Integer orderDetailCode;

    @Column(name = "order_detail_price", nullable = false)
    private Integer orderDetailPrice;

    @Column(name = "order_detail_quantity", nullable = false)
    private Integer orderDetailQuantity;
    // FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_code")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code")
    private Product product;

    public static OrderDetail createOrderProduct(Product product, int orderDetailQuantity) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setOrderDetailQuantity(orderDetailQuantity);
        orderDetail.setOrderDetailPrice(product.getProductPrice());
        product.removeStock(orderDetailQuantity);
        return orderDetail;
    }

    public int getTotalPrice() {
        return orderDetailPrice * orderDetailQuantity;
    }

    public void cancel() {
        this.getProduct().addStock(orderDetailQuantity);
    }
}