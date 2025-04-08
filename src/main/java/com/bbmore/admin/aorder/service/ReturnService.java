package com.bbmore.admin.aorder.service;

import com.bbmore.admin.aorder.dto.ReturnSearchResultDTO;
import com.bbmore.admin.aorder.repository.ReturnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReturnService {


    private final ReturnRepository returnRepository;

    public List<ReturnSearchResultDTO> searchReturns(String returnCode, Boolean returnStatus, String memberName, LocalDate startDate, LocalDate endDate) {
        String rc = (returnCode == null || returnCode.isBlank()) ? null : returnCode;
        String mn = (memberName == null || memberName.isBlank()) ? null : memberName;
        // returnStatus는 Boolean 타입이므로 null일 경우 그대로 전달

        return returnRepository.findReturnDetails(rc, returnStatus, mn, startDate, endDate);
    }
}
