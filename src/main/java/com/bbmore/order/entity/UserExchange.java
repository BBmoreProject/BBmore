package com.bbmore.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Table(name = "tbl_exchange")
public class UserExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchange_code")
    private Integer exchangeCode;           // 교환고유번호

    @Column(name = "exchange_request_date", nullable = false)
    private LocalDate exchangeRequestDate;    // 교환신청일자

    @Column(name = "exchange_status", nullable = false)
    private Boolean exchangeStatus;     // 교환처리상태

    @Column(name = "exchange_reason")
    private String exchangeReason;     // 교환사유


}
