package com.bbmore.admin.adelivery.controller;

import com.bbmore.admin.adelivery.service.adeliveryService;
import com.bbmore.admin.aorder.dto.aOrderSearchResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class adeliveryController {

    private final adeliveryService adeliveryService;

    @GetMapping("/search")
    @ResponseBody
    public List<Map<String, Object>> searchOrders(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<aOrderSearchResultDTO> dtoList = adeliveryService.searchOrders(code, name, phone, startDate, endDate);

        return dtoList.stream().map(dto -> {
            Map<String, Object> map = new HashMap<>();
            map.put("orderCode", dto.getOrderCode());
            map.put("orderDate", dto.getOrderDate());
            map.put("productName", dto.getProductName());

            // 프론트에서 요구하는 키에 맞게 매핑
            map.put("recipientName", dto.getUserName());
            map.put("recipientPhone", dto.getUserPhoneNumber());
            map.put("recipientAddress", dto.getUserAddress());
            return map;
        }).collect(Collectors.toList());
    }
}

