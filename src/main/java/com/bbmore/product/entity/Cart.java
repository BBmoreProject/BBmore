package com.bbmore.product.entity;

import com.bbmore.product.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart")
@Getter
@Setter
@ToString
public class Cart extends BaseTimeEntity {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * Cart와 Member 간에 일대일 관계가 있음을 나타냅니다. 한 회원은 하나의 장바구니만 가질 수 있고, 한 장바구니는 하나의 회원에게만 속합니다.
     * @JoinColumn(name = "member_id")는 외래 키(foreign key)의 이름을 "member_id"로 지정합니다. 이 열은 Cart 테이블에서 Member 테이블을 참조하는 데 사용됩니다.
     */
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
