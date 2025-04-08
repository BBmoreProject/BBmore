package com.bbmore.admin.aorder.controller;


import com.bbmore.admin.aorder.dto.ReturnSearchResultDTO;
import com.bbmore.admin.aorder.service.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class ReturnController {

    private final ReturnService returnService;

    // 생성자 주입
    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

    @GetMapping("/search")
    public Page<ReturnSearchResultDTO> searchReturns(
            @RequestParam(required = false) String returnCode,
            @RequestParam(required = false) Boolean returnStatus,
            @RequestParam(required = false) String memberName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PageableDefault(size = 8) Pageable pageable) {
        return returnService.searchReturns(returnCode, returnStatus, memberName, startDate, endDate, pageable);
    }
}
