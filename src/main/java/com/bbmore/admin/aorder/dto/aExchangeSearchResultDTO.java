package com.bbmore.admin.aorder.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class aExchangeSearchResultDTO {

    private Integer exchangeCode;           // 교환코드
    private LocalDate exchangeRequestDate;  // 교환신청일자
    private Boolean exchangeStatus;         // 교환처리상태
    private String exchangeReason;          // 교환사유
    private String memberName;              // 회원이름
}
