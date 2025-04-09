package com.bbmore.admin.adelivery.repository;


import com.bbmore.admin.aorder.dto.aOrderSearchResultDTO;
import com.bbmore.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface adeliveryRepository extends JpaRepository<OrderDetail, Long> {

    @Query("""
  SELECT new com.bbmore.admin.aorder.dto.aOrderSearchResultDTO(
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
  WHERE (:code IS NULL OR o.orderCode = :code)
    AND (:name IS NULL OR m.userName LIKE CONCAT('%', :name, '%'))
    AND (:phone IS NULL OR m.userPhoneNumber LIKE CONCAT('%', :phone, '%'))
    AND (:startDate IS NULL OR o.orderDate >= :startDate)
    AND (:endDate IS NULL OR o.orderDate <= :endDate)
""")
    List<aOrderSearchResultDTO> findOrderDetails(
            @Param("code") Integer code,
            @Param("name") String name,
            @Param("phone") String phone,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
