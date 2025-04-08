package com.bbmore.admin.aorder.service;

import com.bbmore.admin.aorder.dto.aReturnSearchResultDTO;
import com.bbmore.admin.aorder.repository.aReturnRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class aReturnService {

    private final aReturnRepository aReturnRepository;

    public aReturnService(aReturnRepository aReturnRepository) {
        this.aReturnRepository = aReturnRepository;
    }

    public Page<aReturnSearchResultDTO> searchReturns(
            String returnCode,
            Boolean returnStatus,
            String memberName,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable) {
        String rc = (returnCode == null || returnCode.isBlank()) ? null : returnCode;
        String mn = (memberName == null || memberName.isBlank()) ? null : memberName;
        return aReturnRepository.findReturnDetailsPage(rc, returnStatus, mn, startDate, endDate, pageable);
    }

    @Transactional
    public void updateReturnStatus(Integer returnCode, Boolean status) {
        int updated = aReturnRepository.updateReturnStatus(returnCode, status);
        if (updated == 0) {
            throw new EntityNotFoundException("반품코드 " + returnCode + " 를 찾을 수 없습니다.");
        }
    }
}
