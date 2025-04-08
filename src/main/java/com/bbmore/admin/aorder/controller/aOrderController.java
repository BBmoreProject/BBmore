package com.bbmore.admin.aorder.controller;

import com.bbmore.admin.aorder.dto.aOrderSearchResultDTO;
import com.bbmore.admin.aorder.service.aOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class aOrderController {

    private final aOrderService aOrderService;

    @GetMapping("/search")
    @ResponseBody
    public Page<aOrderSearchResultDTO> searchOrders(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PageableDefault(size = 8) Pageable pageable
    ) {
        return aOrderService.searchOrders(code, name, phone, startDate, endDate, pageable);
    }
}

