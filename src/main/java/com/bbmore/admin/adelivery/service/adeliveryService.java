package com.bbmore.admin.adelivery.service;


import com.bbmore.admin.adelivery.repository.adeliveryRepository;
import com.bbmore.admin.aorder.dto.aOrderSearchResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class adeliveryService {

    private final adeliveryRepository adeliveryRepository;

    public List<aOrderSearchResultDTO> searchOrders(
            String code, String name, String phone,
            LocalDate startDate, LocalDate endDate) {

        Integer parsedCode = null;

        if (code != null && !code.trim().isEmpty()) {
            try {
                parsedCode = Integer.parseInt(code.trim());
            } catch (NumberFormatException e) {

                return new ArrayList<>();
            }
        }

        return adeliveryRepository.findOrderDetails
                (parsedCode, name, phone, startDate, endDate);
    }

}
