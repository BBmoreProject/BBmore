package com.bbmore.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 기본 생성자, 외부에서 직접 호출 방지
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
    public Animal(String animalType, String animalBreed) {
        this.animalType = animalType;
        this.animalBreed = animalBreed;
    }

    // 기존 객체의 필드를 직접 수정하는 메서드 추가
    public void updateAnimalInfo(String animalType, String animalBreed) {
        this.animalType = animalType;
        this.animalBreed = animalBreed;
    }


}
