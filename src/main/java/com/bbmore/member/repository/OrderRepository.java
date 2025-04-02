package com.bbmore.member.repository;

import com.bbmore.member.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT new com.bbmore.member.dto.PurchaseListDTO" +
            "(o.orderTotalPrice, o.orderDate, o.orderStatus, " +
            "p.productName, p.productImg, od.orderDetailPrice, od.orderDetailQuantity) " +
            "FROM Order o " +
            "JOIN o.member m " +
            "JOIN OrderDetail od ON o.orderCode = od.order.orderCode " +
            "JOIN Product p ON od.product.productCode = p.productCode " +
            "WHERE m.userCode = :userCode")
    List<com.bbmore.member.dto.PurchaseListDTO> findUserPurchaseList(@Param("userCode") Integer userCode);
}
