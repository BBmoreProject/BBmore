package com.bbmore.admin.aorder.controller;


import com.bbmore.admin.aorder.dto.aReturnSearchResultDTO;
import com.bbmore.admin.aorder.service.aReturnService;
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
@RequestMapping("/api/returns")
@RequiredArgsConstructor
public class aReturnController {

    private final aReturnService aReturnService;


    @GetMapping("/search")
    @ResponseBody
    public Page<aReturnSearchResultDTO> searchReturns(
            @RequestParam(required = false) String returnCode,
            @RequestParam(required = false) Boolean returnStatus,
            @RequestParam(required = false) String memberName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PageableDefault(size = 8) Pageable pageable) {
        return aReturnService.searchReturns(returnCode, returnStatus, memberName, startDate, endDate, pageable);
    }

    @PutMapping("/{returnCode}/status")
    @ResponseBody
    public ResponseEntity<Void> updateStatus(
            @PathVariable Integer returnCode,
            @RequestParam Boolean status
    ) {
        aReturnService.updateReturnStatus(returnCode, status);
        return ResponseEntity.ok().build();
    }
}
