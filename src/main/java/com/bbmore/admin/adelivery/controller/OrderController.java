package com.bbmore.admin.adelivery.controller;


import com.bbmore.admin.adelivery.service.OrderService;
import com.bbmore.admin.aorder.dto.OrderSearchResultDTO;
import com.bbmore.order.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
// api
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/search")
    public List<OrderSearchResultDTO> searchOrders(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return orderService.searchOrders(code, name, phone, startDate, endDate);
    }
}
