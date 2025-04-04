package com.bbmore.admin.adelivery.service;

import com.bbmore.admin.adelivery.repository.OrderRepository;
import com.bbmore.order.dto.OrderDto;
import com.bbmore.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDto> searchOrders(
            String code, String name, String phone,
            LocalDate startDate, LocalDate endDate
    ) {
        String c = (code == null || code.isBlank()) ? null : code;
        String n = (name == null || name.isBlank()) ? null : name;
        String p = (phone == null || phone.isBlank()) ? null : phone;

        List<Order> orders = orderRepository.searchOrders(c, n, p, startDate, endDate);

        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private OrderDto toDto(Order entity) {
        return OrderDto.builder()
                .orderCode(entity.getOrderCode())
                .orderDate(entity.getOrderDate())
                .productName(entity.getProductName())
                .recipientName(entity.getRecipientName())
                .recipientPhone(entity.getRecipientPhone())
                .recipientAddress(entity.getRecipientAddress())
                .build();
    }
}
