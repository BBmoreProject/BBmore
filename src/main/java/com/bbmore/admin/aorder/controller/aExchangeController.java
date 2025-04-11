package com.bbmore.admin.aorder.controller;

import com.bbmore.admin.aorder.dto.aExchangeSearchResultDTO;
import com.bbmore.admin.aorder.service.aExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/api/exchanges")
@RequiredArgsConstructor
public class aExchangeController {

    private final aExchangeService service;

    @GetMapping("/search")
    @ResponseBody
    public Page<aExchangeSearchResultDTO> searchExchanges(
            @RequestParam(required = false) String exchangeCode,
            @RequestParam(required = false) Boolean exchangeStatus,
            @RequestParam(required = false) String memberName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PageableDefault(size = 8) Pageable pageable) {
        return service.searchExchanges(exchangeCode, exchangeStatus, memberName, startDate, endDate, pageable);
    }

    @PutMapping("/{exchangeCode}/status")
    @ResponseBody
    public ResponseEntity<Void> updateStatus(
            @PathVariable Integer exchangeCode,
            @RequestParam Boolean status) {
        service.updateExchangeStatus(exchangeCode, status);
        return ResponseEntity.ok().build();
    }
}
