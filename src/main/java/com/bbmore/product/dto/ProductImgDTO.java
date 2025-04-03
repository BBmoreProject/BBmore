package com.bbmore.product.dto;

import com.bbmore.product.entity.ProductImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ProductImgDTO {
    private Long id;
    private String productImgName;
    private String productOriginalImgName;
    private String productImgUrl;
    private String representativeImg;  // 엔티티와 일치

    private static ModelMapper modelMapper = new ModelMapper();

    public static ProductImgDTO of(ProductImg productImg) {
        return modelMapper.map(productImg, ProductImgDTO.class);
    }
}
