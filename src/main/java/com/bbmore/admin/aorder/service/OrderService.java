package com.bbmore.admin.aorder.service;


import com.bbmore.admin.aorder.dto.OrderSearchResultDTO;
import com.bbmore.admin.aorder.repository.OrderRepository;
import com.bbmore.member.dto.MemberDTO;
import com.bbmore.order.dto.OrderDTO;
import com.bbmore.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderSearchResultDTO> searchOrders(String code, String name, String phone, LocalDate startDate, LocalDate endDate) {
        String c = (code == null || code.isBlank()) ? null : code;
        String n = (name == null || name.isBlank()) ? null : name;
        String p = (phone == null || phone.isBlank()) ? null : phone;

        return orderRepository.findOrderDetails(c, n, p, startDate, endDate);
    }
}
