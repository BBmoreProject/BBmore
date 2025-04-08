package com.bbmore.admin.aorder.service;


import com.bbmore.admin.aorder.dto.OrderSearchResultDTO;
import com.bbmore.admin.aorder.repository.OrderRepository;
import com.bbmore.member.dto.MemberDTO;
import com.bbmore.order.dto.OrderDTO;
import com.bbmore.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<OrderSearchResultDTO> searchOrders(
            String code, String name, String phone,
            LocalDate startDate, LocalDate endDate,
            Pageable pageable) {

        String c = (code == null || code.isBlank()) ? null : code;
        String n = (name == null || name.isBlank()) ? null : name;
        String p = (phone == null || phone.isBlank()) ? null : phone;

        return orderRepository.findOrderDetailsPage(c, n, p, startDate, endDate, pageable);
    }
}

