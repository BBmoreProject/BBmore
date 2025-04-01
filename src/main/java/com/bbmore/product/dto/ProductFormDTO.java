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
    } /// createProduct() 메서드: ProductFormDTO 객체를 Product 엔티티로 변환합니다.
    /// this(현재 ProductFormDTO 인스턴스)의 모든 필드 값을
    /// Product 클래스의 동일한 이름의 필드에 자동으로 복사합니다.

    public static ProductFormDTO of(Product product){
        return modelMapper.map(product, ProductFormDTO.class); /// DTO 데이터 복사
        /**
         * 매개변수로 받은 Product 엔티티의 필드 값을
         * ProductFormDTO 클래스의 동일한 이름의 필드에 자동으로 복사합니다.
         */
    }


}
