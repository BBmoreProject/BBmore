package com.bbmore.order.repository;

import com.bbmore.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository <OrderDetail, Integer>
{
}
