package com.bbmore.member.dto;

import com.bbmore.member.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    private Boolean userIsdeleted;         // 회원탈퇴여부
//
//    private UserRole userAccessLevel;      // 회원권한

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

    private Integer animalCode;            // fk 동물고유번호

    @Builder
    public MemberDTO(Integer userCode, String userId, String userPassword, String userName, String userAddress,
                     String userPhoneNumber, String userEmail, String userPetName, Integer userPetAge,
                     Integer userPetWeight, String userPetMedicalHistory, AnimalDTO animalDTO) {
        this.userCode = userCode;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
        this.userPetName = userPetName;
        this.userPetAge = userPetAge;
        this.userPetWeight = userPetWeight;
        this.userPetMedicalHistory = userPetMedicalHistory;
        this.animalDTO = animalDTO;
    }

    private Integer membershipCode;        // fk 회원등급고유번호
    public MemberDTO(Member member) {
        this.userId = member.getUserId();
        this.userPassword = member.getUserPassword();
        this.userName = member.getUserName();
        this.userAddress = member.getUserAddress();
        this.userPhoneNumber = member.getUserPhoneNumber();
        this.userEmail = member.getUserEmail();
        this.userPetName = member.getUserPetName();
        this.userPetAge = member.getUserPetAge();
        this.userPetWeight = member.getUserPetWeight();
        this.userPetMedicalHistory = member.getUserPetMedicalHistory();
        this.animalDTO = new AnimalDTO(member.getAnimal());  // AnimalDTO로 변환하여 전달

    }

}
