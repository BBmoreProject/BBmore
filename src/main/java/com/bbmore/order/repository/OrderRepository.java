package com.bbmore.order.repository;

import com.bbmore.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o JOIN o.member m WHERE m.userName = :username AND o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findOrdersByUsernameAndDateRange(
            @Param("username") String userName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
