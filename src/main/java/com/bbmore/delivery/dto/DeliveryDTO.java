package com.bbmore.delivery.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryDTO {


    private Integer deliveryCode;       // 회원배송지고유번호

    private String deliveryName;        // 회원배송지이름

    private String deliveryAddress;     // 회원배송지주소

    private String deliveryPhone ;      // 회원배송지전화번호

    private String deliveryRequest;     // 배송요청사항


    private Integer userCode;           // fk 회원고유번호

}
