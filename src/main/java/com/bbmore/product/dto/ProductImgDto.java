package com.bbmore.product.dto;

import com.bbmore.product.entity.ProductImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

@Getter
@Setter
public class ProductImgDto {

    private Integer productImgCode;

    private String productImgName;

    private String productOriImgName;

    private String productImgRepYn;

    private static ModelMapper modelMapper = new ModelMapper();

    /// 리플렉션
    public static ProductImgDto of(ProductImg productImg) {
        return modelMapper.map(productImg, ProductImgDto.class);
    }
}
