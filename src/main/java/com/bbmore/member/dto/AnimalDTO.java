package com.bbmore.member.dto;

import com.bbmore.member.entity.Animal;
import lombok.*;


@Getter
@Setter
@ToString
// 04.04 태민 추가
@Builder
// 04.04 태민 추가
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {


    private Integer animalCode; // 동물 고유 코드
    private String animalType;  // 반려동물 종류 (강아지/고양이 등)
    private String animalBreed; // 반려동물 품종


}
