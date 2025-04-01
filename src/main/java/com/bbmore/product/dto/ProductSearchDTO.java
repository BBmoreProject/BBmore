package com.bbmore.product.dto;

import com.bbmore.product.constant.ProductSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchDTO {

    private String searchDateType;

    private ProductSellStatus searchSellStatus;

    private String searchBy;

    private String searchQuery = "";


}
