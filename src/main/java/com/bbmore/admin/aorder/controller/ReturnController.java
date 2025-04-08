package com.bbmore.admin.aorder.controller;


import com.bbmore.admin.aorder.dto.ReturnSearchResultDTO;
import com.bbmore.admin.aorder.service.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/returns")
@RequiredArgsConstructor
public class ReturnController {

    private final ReturnService returnService;

    @GetMapping("/search")
    public List<ReturnSearchResultDTO> searchReturns(
            @RequestParam(required = false) String returnCode,
            @RequestParam(required = false) Boolean returnStatus,
            @RequestParam(required = false) String memberName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return returnService.searchReturns(returnCode, returnStatus, memberName, startDate, endDate);
    }
}
