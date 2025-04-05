package com.bbmore.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {

    private Integer userCode;              // 회원고유번호

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    private String userId;                 // 회원ID

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Length(min=4, max=16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
    private String userPassword;           // 회원비밀번호

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String userName;               // 회원이름

    @NotBlank(message = "주소는 필수 입력값입니다.")
    private String userAddress;            // 회원주소

    private String userPhoneNumber;        // 회원전화번호

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String userEmail;              // 회원이메일

    @NotBlank(message = "반려동물의 이름을 입력해주세요.")
    private String userPetName;

    @Positive(message = "반려동물의 나이는 양수여야 합니다.") // int 타입에서 NotBlank는 의미 없으므로 Positive 사용
    private Integer userPetAge;

    @Positive(message = "반려동물의 몸무게는 양수여야 합니다.") // int 타입에서 NotBlank는 의미 없으므로 Positive 사용
    private Integer userPetWeight;

    private String userPetMedicalHistory;

    private AnimalDTO animalDTO; // 동물 정보 포함

//    public AnimalDTO getAnimalDTO() {
//        return animalDTO;
//    }
//
//    public void setAnimalDTO(AnimalDTO animalDTO) {
//        this.animalDTO = animalDTO;
//    }

}
