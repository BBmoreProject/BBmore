package com.bbmore.delivery.entity;


import com.bbmore.member.entity.Animal;
import com.bbmore.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Table(name = "tbl_delivery")
public class Delivery {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_code")
    private Integer deliveryCode;  // 회원배송지고유번호

    @Column(name = "delivery_name")
    private String deliveryName;     //  회원배송지이름

    @Column(name = "delivery_address")
    private String deliveryAddress;   //  회원배송지주소

    @Column(name = "delivery_phone")
    private String deliveryPhone;       //  회원배송지전화번호

    @Column(name = "delivery_request")
    private String deliveryRequest;     //  배송요청사항


    // fk(회원고유번호)
    @ManyToOne
    @JoinColumn(name = "user_code")
    private Member member;


}
