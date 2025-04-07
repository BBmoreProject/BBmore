package com.bbmore.order.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReturnDTO {

    private Integer returnCode;              // 반품고유번호

    private LocalDate returnRequestDate;     // 반품신청일자

    private Boolean returnStatus;            // 반품처리상태

    private Integer refundAmount;             // 환불금액

    private String returnReason;             // 반품사유

}
