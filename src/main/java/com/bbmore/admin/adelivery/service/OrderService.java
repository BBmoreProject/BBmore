package com.bbmore.admin.adelivery.service;

import com.bbmore.admin.adelivery.dto.OrderDto;
import com.bbmore.admin.adelivery.entity.Order;
import com.bbmore.admin.adelivery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    // 검색
    public List<OrderDto> searchOrders(String code, String name, String phone, LocalDate startDate, LocalDate endDate) {
        String c = (code == null || code.isBlank()) ? null : code;
        String n = (name == null || name.isBlank()) ? null : name;
        String p = (phone == null || phone.isBlank()) ? null : phone;

        List<Order> orders = orderRepository.searchOrders(c, n, p, startDate, endDate);
        return orders.stream().map(this::toDto).collect(Collectors.toList());
    }


    private OrderDto toDto(Order o) {
//        System.out.println("USER: " + o.getRecipientName() + ", PHONE: " + o.getRecipientPhone());

        return OrderDto.builder()
                .orderCode(o.getOrderCode())
                .orderDate(o.getOrderDate())
                .productName(o.getProductName())
                .recipientName(o.getRecipientName())
                .recipientPhone(o.getRecipientPhone())
                .recipientAddress(o.getRecipientAddress())
                .build();
    }


}
