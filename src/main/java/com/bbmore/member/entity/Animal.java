package com.bbmore.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_animal")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_code")
    private Integer animalCode;  // 동물 고유 번호

    @Column(name = "animal_type")
    private String animalType; // 분류 (강아지 / 고양이)

    @Column(name = "animal_breed")
    private String animalBreed;    // 품종


    // 04.04 태민 추가
    @ManyToOne
    @JoinColumn(name = "user_code", referencedColumnName = "user_code")
    private Member member; // Member 테이블과 관계 설정

}
