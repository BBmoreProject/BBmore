package com.bbmore.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateDTO {

    private String userId;
    private String userPassword;
    private String userName;
    private String userAddress;
    private String userPhoneNumber;
    private String userEmail;
    private String userPetName;
    private Integer userPetAge;
    private Integer userPetWeight;
    private String userPetMedicalHistory;


    private Integer animalCode;

    private String animalType;  // 동물 타입 업데이트

    @NotBlank(message = "Animal breed cannot be blank")  // 필수값으로 설정
    private String animalBreed; // 동물 품종 업데이트



//    public Integer getAnimalCode() {
//        return animalCode;
//    }
//
//    public void setAnimalCode(Integer animalCode) {
//        this.animalCode = animalCode;
//    }


}
