package com.bbmore.admin.aorder.service;

import com.bbmore.admin.aorder.dto.ReturnSearchResultDTO;
import com.bbmore.admin.aorder.repository.ReturnRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReturnService {

    private final ReturnRepository returnRepository;

    public ReturnService(ReturnRepository returnRepository) {
        this.returnRepository = returnRepository;
    }

    public Page<ReturnSearchResultDTO> searchReturns(
            String returnCode,
            Boolean returnStatus,
            String memberName,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable) {
        String rc = (returnCode == null || returnCode.isBlank()) ? null : returnCode;
        String mn = (memberName == null || memberName.isBlank()) ? null : memberName;
        return returnRepository.findReturnDetailsPage(rc, returnStatus, mn, startDate, endDate, pageable);
    }

    @Transactional
    public void updateReturnStatus(Integer returnCode, Boolean status) {
        int updated = returnRepository.updateReturnStatus(returnCode, status);
        if (updated == 0) {
            throw new EntityNotFoundException("반품코드 " + returnCode + " 를 찾을 수 없습니다.");
        }
    }
}
