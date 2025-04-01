package com.bbmore.product.repository;

import com.bbmore.product.dto.ProductSearchDTO;
import com.bbmore.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<Product> getAdminProductPage(ProductSearchDTO productSearchDTO, Pageable pageable);
}
