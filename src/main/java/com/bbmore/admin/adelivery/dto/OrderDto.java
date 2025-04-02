package com.bbmore.admin.adelivery.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class OrderDto {
    private int orderCode;
    private LocalDate orderDate;
    private String productName;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
}
