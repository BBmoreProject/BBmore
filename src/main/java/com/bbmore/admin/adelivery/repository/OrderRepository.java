package com.bbmore.admin.adelivery.repository;

import com.bbmore.admin.aorder.dto.OrderSearchResultDTO;
import com.bbmore.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("""
    SELECT new com.bbmore.admin.aorder.dto.OrderSearchResultDTO(
        o.orderCode,
        o.orderDate,
        p.productName,
        m.userName,
        m.userPhoneNumber,
        m.userAddress
    )
    FROM OrderDetail od
    JOIN od.order o
    JOIN o.member m
    JOIN od.product p
    WHERE (:code IS NULL OR CAST(o.orderCode AS string) = :code)
      AND (:name IS NULL OR m.userName LIKE %:name%)
      AND (:phone IS NULL OR m.userPhoneNumber LIKE %:phone%)
      AND (:startDate IS NULL OR o.orderDate >= :startDate)
      AND (:endDate IS NULL OR o.orderDate <= :endDate)
""")
    List<OrderSearchResultDTO> findOrderDetails(
            @Param("code") String code,
            @Param("name") String name,
            @Param("phone") String phone,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}

