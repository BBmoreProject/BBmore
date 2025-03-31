package com.bbmore.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_member")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_code")
    private int animalCode;

    @Column(name = "animal_type")
    private String animalType;

    @Column(name = "animal_breed")
    private String animalBreed;

    @ManyToOne
    @JoinColumn(name = "user_code", referencedColumnName = "user_code")
    private Member member; // Member 테이블과 관계 설정

    // Getters and setters

}
