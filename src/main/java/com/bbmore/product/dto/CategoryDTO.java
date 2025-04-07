package com.bbmore.product.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

    private Integer categoryCode;           // 카테고리고유번호

    private String categoryName;            // 카테고리이름

}
