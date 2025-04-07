package com.bbmore.admin.adelivery.repository;

import com.bbmore.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {


    // %LIKE% 말고 = :name(WHERE userName = ' ')사용하여 같아야만 검색
    // 회원전화번호 같아야만 검색으로 변경
    @Query("""
            SELECT o FROM Order o
             WHERE (:code IS NULL OR CAST(o.orderCode AS string) = :code)
               AND (:name IS NULL OR o.recipientName = :name)
               AND (:phone IS NULL OR o.recipientPhone = :phone)
               AND (:startDate IS NULL OR o.orderDate >= :startDate)
               AND (:endDate IS NULL OR o.orderDate <= :endDate)
            """)
    List<Order> searchOrders(
            @Param("code") String code,
            @Param("name") String name,
            @Param("phone") String phone,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
