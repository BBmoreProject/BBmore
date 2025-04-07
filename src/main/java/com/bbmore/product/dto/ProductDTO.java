package com.bbmore.product.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Integer productCode;           // 상품고유번호

    private String productName;            // 상품이름

    private Integer productPrice;           // 상품금액

    private Integer productQuantity;        // 상품재고


    private Integer categoryCode;            // fk 카테고리고유번호

}
