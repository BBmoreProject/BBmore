package com.bbmore.order.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserExchangeDTO {

    private Integer exchangeCode;              // 교환고유번호

    private LocalDate exchangeRequestDate;     // 교환신청일자

    private Boolean exchangeStatus;            // 교환처리상태

    private String exchangeReason;             // 교환사유

}
