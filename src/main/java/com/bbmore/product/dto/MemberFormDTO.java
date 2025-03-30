package com.bbmore.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * 회원 가입 화면으로부터 넘어오는 가입정보를 담을 DTO
 */
public class MemberFormDTO {

    @NotBlank(message = "이름은 필수 입력 값입니다")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다")
    private String password;

    @NotBlank(message = "주소는 필수 입력 값입니다")
    private String address;


}
