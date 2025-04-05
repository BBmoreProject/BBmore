package com.bbmore.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "tbl_animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_code")
    private Integer animalCode;  // 동물 고유 번호

    @Column(name = "animal_type")
    private String animalType; // 분류 (강아지 / 고양이)

    @Column(name = "animal_breed")
    private String animalBreed;    // 품종



}
