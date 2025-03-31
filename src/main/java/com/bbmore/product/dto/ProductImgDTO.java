package com.bbmore.product.dto;

import com.bbmore.product.entity.ProductImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ProductImgDTO {

    /**
     * of(ProductImg productImg) 메소드는 ProductImg 엔티티를 받아 해당하는 ProductImgDTO 객체로 변환하여 반환합니다.
     * 이 메소드를 통해 엔티티를 DTO로 쉽게 변환할 수 있습니다.
     *
     * Java 객체 간의 매핑을 자동화하는 라이브러리 = modelMapper
     *
     * 구체적으로 설명하자면:
     *
     * of(ProductImg productImg) 메소드는 ProductImg 타입의 인자를 받습니다.
     * modelMapper.map(productImg, ProductImgDTO.class)는 다음 작업을 수행합니다:
     *
     * 첫 번째 인자인 productImg 객체(소스 객체)에서 필드 값을 읽습니다.
     * 두 번째 인자인 ProductImgDTO.class로 지정된 타입(대상 타입)의 새 인스턴스를 생성합니다.
     * 소스 객체의 필드들을 대상 객체의 동일한 이름을 가진 필드들에 자동으로 복사합니다.
     * 예를 들어, productImg.id → productImgDTO.id, productImg.productImgName → productImgDTO.productImgName 등으로 값이 복사됩니다.
     *
     *
     * 변환된 ProductImgDTO 객체를 반환합니다.
     * 이 코드의 정적(static) 메소드 of를 사용하면 다음과 같은 방식으로 간편하게 DTO로 변환할 수 있습니다:
     */

    private Long id;

    private String productImgName;

    private String originalImgName;

    private String productImgUrl;

    private String representativeImgUrl;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ProductImgDTO of(ProductImg productImg) {
        return modelMapper.map(productImg, ProductImgDTO.class);
    }
}
