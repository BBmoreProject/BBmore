package com.bbmore.product.repository;

import com.bbmore.product.dto.MainProductDto;
import com.bbmore.product.dto.ProductSearchDto;
import com.bbmore.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<Product> getAdminProductPage(ProductSearchDto productSearchDto,
                                      Pageable pageable);

    Page<MainProductDto> getMainProductPage(ProductSearchDto productSearchDto,
                                            Pageable pageable);
}
