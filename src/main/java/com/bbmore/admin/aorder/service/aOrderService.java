package com.bbmore.admin.aorder.service;


import com.bbmore.admin.aorder.dto.aOrderSearchResultDTO;
import com.bbmore.admin.aorder.repository.aOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class aOrderService {

    private final aOrderRepository aOrderRepository;

    public aOrderService(aOrderRepository aOrderRepository) {
        this.aOrderRepository = aOrderRepository;
    }

    public Page<aOrderSearchResultDTO> searchOrders(
            String code, String name, String phone,
            LocalDate startDate, LocalDate endDate,
            Pageable pageable) {

        String c = (code == null || code.isBlank()) ? null : code;
        String n = (name == null || name.isBlank()) ? null : name;
        String p = (phone == null || phone.isBlank()) ? null : phone;

        return aOrderRepository.findOrderDetailsPage(c, n, p, startDate, endDate, pageable);
    }
}

