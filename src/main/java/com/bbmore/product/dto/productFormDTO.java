package com.bbmore.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class productFormDTO {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다")
    private String productName;


}
