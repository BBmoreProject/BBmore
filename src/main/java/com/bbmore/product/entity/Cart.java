package com.bbmore.product.entity;

import com.bbmore.product.config.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart")
@Getter
@Setter
@ToString
public class Cart extends BaseEntity {

    @Id
    @Column(name = "cart_code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartCode;

    @OneToOne
    @JoinColumn(name = "member_code")
    private Member member;


}
