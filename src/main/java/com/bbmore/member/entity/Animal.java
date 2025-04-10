package com.bbmore.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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

    @Builder
    public Animal(Integer animalCode, String animalBreed) {
        this.animalCode = animalCode;
        this.animalBreed = animalBreed;
    }

    // 기존 객체의 필드를 직접 수정하는 메서드 추가
    public void updateAnimalInfo(Integer animalCode, String animalType, String animalBreed) {
        this.animalCode = animalCode;
        this.animalType = animalType;
        this.animalBreed = animalBreed;
    }


}
