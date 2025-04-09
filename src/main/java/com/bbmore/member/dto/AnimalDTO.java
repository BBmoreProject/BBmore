package com.bbmore.member.dto;

import com.bbmore.member.entity.Animal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalDTO {

    private String userId; // 왜 추가하신거지..????

    private Integer animalCode; // 동물 고유 코드
    private String animalType;  // 반려동물 종류 (강아지/고양이 등)
    private String animalBreed; // 반려동물 품종

    public AnimalDTO(Animal animal) {
        this.animalCode = animal.getAnimalCode();
        this.animalType = animal.getAnimalType();
        this.animalBreed = animal.getAnimalBreed();
    }

}
