package com.bbmore.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainProductDto {

    private Integer productCode;

    private String productName;

    private String productDetail;

    private String imgUrl;

    private Integer price;

    @QueryProjection
    public MainProductDto(Integer productCode, String productName, String productDetail,
                          String imgUrl, Integer price) {
        this.productCode = productCode;
        this.productName = productName;
        this.productDetail = productDetail;
        this.imgUrl = imgUrl;
        this.price = price;
    }
}

