package com.bbmore.product.repository;


import com.bbmore.product.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    ///  간단한 쿼리문은 Querydsl 안쓰고 JPQL로 작성

    @Query("select o from Order o " +
            "where o.member.email = :email " +
            "order by o.orderDate desc"
    )
    List<Order> findOrders(@Param("email") String email, Pageable pageable);

    @Query("select count(o) from Order o " +
            "where o.member.email = :email"
    )
    Long countOrder(@Param("email") String email);
}