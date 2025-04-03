package com.bbmore.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AnimalDTO {

    private int animalCode;
    private String animalType;
    private String animalBreed;
}
