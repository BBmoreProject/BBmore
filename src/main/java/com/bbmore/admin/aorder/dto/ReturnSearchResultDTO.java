package com.bbmore.admin.aorder.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnSearchResultDTO {

    private Integer returnCode;            // 반품코드
    private LocalDate returnRequestDate;   // 반품신청일자
    private Boolean returnStatus;          // 반품처리상태
    private Integer refundAmount;          // 환불금액
    private String returnReason;           // 반품사유
    private String memberName;             // 회원이름

}
