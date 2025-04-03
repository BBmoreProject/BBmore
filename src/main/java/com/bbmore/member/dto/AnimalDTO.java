package com.bbmore.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AnimalDTO {


    private Integer animalCode; // 동물 고유 코드
    private String animalType;  // 반려동물 종류 (강아지/고양이 등)
    private String animalBreed; // 반려동물 품종


}
