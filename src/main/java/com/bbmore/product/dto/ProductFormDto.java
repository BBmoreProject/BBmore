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
public class ProductFormDto {

    private Integer productCode;

    @NotBlank(message = "상품명은 필수 입력 값입니다")
    private String productName;

    @NotNull(message = "가격은 필수 입력 값입니다")
    private Integer productPrice;

    @NotBlank(message = "상품 설명은 필수 입력 값입니다.")
    private String productDetail;  // Integer에서 String으로 변경

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer productQuantity;

    private ProductSellStatus productSellStatus;

    private List<ProductImgDto> productImgDtoList = new ArrayList<>();

    private List<Integer> productImgCodeList = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    /// 인스턴스 메서드 this 로 현재 DTO 데이터를 Product 엔티티로 변환
    public Product createProduct() {
        return modelMapper.map(this, Product.class);
    }

    /// 외부에서 받은 Product 객체를 ProductFormDto로 변환
    public static ProductFormDto of(Product product) {  // 반환 타입을 ProductFormDto로 수정
        return modelMapper.map(product, ProductFormDto.class);  // 매핑 대상도 ProductFormDto로 수정
    }
}