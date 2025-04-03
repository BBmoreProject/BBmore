package com.bbmore.product.dto;

import com.bbmore.product.constant.ProductSellStatus;
import com.bbmore.product.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductFormDTO {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다")
    private String productName;

    @NotNull(message = "가격은 필수 입력 값입니다")
    private Integer price;

    @NotBlank(message = "이름은 필수 입력 값입니다")
    private String productDetail;

    @NotNull(message = "재고는 필수 입력 값입니다")
    private Integer stockNumber;

    private ProductSellStatus productSellStatus;

    private List<ProductImgDTO> productImgDTOList = new ArrayList<>();

    private List<Long> productImgIdList = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Product createProduct() {
        return modelMapper.map(this, Product.class);
    }

    public static ProductFormDTO of(Product product) {

        return  modelMapper.map(product, ProductFormDTO.class);
    }



}