package com.bbmore.admin.aorder.service;

import com.bbmore.admin.aorder.dto.aExchangeSearchResultDTO;
import com.bbmore.admin.aorder.repository.aExchangeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class aExchangeService {

    private final aExchangeRepository repo;

    public aExchangeService(aExchangeRepository repo) {
        this.repo = repo;
    }

    public Page<aExchangeSearchResultDTO> searchExchanges(
            String exchangeCode,
            Boolean exchangeStatus,
            String memberName,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable) {

        String ec = (exchangeCode == null || exchangeCode.isBlank()) ? null : exchangeCode;
        String mn = (memberName == null || memberName.isBlank()) ? null : memberName;
        return repo.findExchangeDetailsPage(ec, exchangeStatus, mn, startDate, endDate, pageable);
    }

    @Transactional
    public void updateExchangeStatus(Integer exchangeCode, Boolean status) {
        int updated = repo.updateExchangeStatus(exchangeCode, status);
        if (updated == 0) {
            throw new EntityNotFoundException("교환코드 " + exchangeCode + " 를 찾을 수 없습니다.");
        }
    }
}
