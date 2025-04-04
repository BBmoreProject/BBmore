package com.bbmore.order.dto;

import com.bbmore.member.dto.MemberDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
public class OrderDTO {
    private Integer orderCode;
    private Integer orderTotalPrice;
    private LocalDate orderDate;
    private String orderStatus;
    private String recipientAddress;
    private String recipientPhone;
    private String orderDeliveryRequest;
    private String productName;
    private Integer productQuantity;

    private Integer userCode;
    private Integer orderDetailCode;

    private MemberDTO member;


}

