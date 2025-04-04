package com.bbmore.order.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

// 0404 DTO생성
@Getter
@Builder
public class OrderDto {
    private final Integer orderCode;
    private final LocalDate orderDate;
    private final String productName;
    private final String recipientName;
    private final String recipientPhone;
    private final String recipientAddress;
}
