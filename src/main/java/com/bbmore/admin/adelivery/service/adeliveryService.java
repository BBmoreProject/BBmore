package com.bbmore.admin.adelivery.service;

import com.bbmore.admin.adelivery.repository.adeliveryRepository;
import com.bbmore.admin.aorder.dto.OrderSearchResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class adeliveryService {

    private final adeliveryRepository adeliveryRepository;

    public List<OrderSearchResultDTO> searchOrders(String code, String name, String phone,
                                                   LocalDate startDate, LocalDate endDate) {
        String c = (code == null || code.isBlank()) ? null : code;
        String n = (name == null || name.isBlank()) ? null : name;
        String p = (phone == null || phone.isBlank()) ? null : phone;

        return adeliveryRepository.findOrderDetails(c, n, p, startDate, endDate);
    }
}
