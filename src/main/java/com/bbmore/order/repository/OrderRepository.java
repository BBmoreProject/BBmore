package com.bbmore.order.repository;

import com.bbmore.order.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o " +
    "where o.member.userEmail = :email " +
    "order by o.orderDate desc")
    List<Order> findOrders(@Param("userEmail") String userEmail, Pageable pageable);

    @Query("select count(o) from Order o " +
    "where o.member.userEmail = :userEmail")
    Long countOrder(@Param("userEmail") String userEmail);
}
