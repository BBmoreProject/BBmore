package com.bbmore.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFormDto {

    @NotBlank(message = "이름은 필수 입력 값입니다")
    private String userName;

    @NotEmpty(message = "이메일은 필수 입력 값입니다")
    private String userEmail;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다")
    private String userPassword;

    @NotEmpty(message = "주소는 필수 입력 값입니다")
    private String userAddress;
}
