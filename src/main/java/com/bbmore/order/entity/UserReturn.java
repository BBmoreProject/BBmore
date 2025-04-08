package com.bbmore.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Table(name = "tbl_return")
public class UserReturn {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_code")
    private Integer returnCode;           // 반품고유번호

    @Column(name = "return_request_date", nullable = false)
    private LocalDate returnRequestDate;    // 반품신청일자

    @Column(name = "return_status", nullable = false)
    private Boolean returnStatus;     // 반품처리상태

    @Column(name = "refund_amount", nullable = false)
    private Integer refundAmount;     // 환불금액

    @Column(name = "return_reason")
    private String returnReason;     // 반품사유


}
