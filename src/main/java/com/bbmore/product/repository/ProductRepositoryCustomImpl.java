package com.bbmore.product.repository;

import com.bbmore.product.constant.ProductSellStatus;
import com.bbmore.product.dto.ProductSearchDTO;
import com.bbmore.product.entity.Product;
import com.bbmore.product.entity.QProduct;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.querydsl.core.types.ExpressionUtils.orderBy;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public ProductRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    ///  특정 판매 상품가 일치하는 동적 쿼리 생성
    private BooleanExpression searchSellStatusEqual(ProductSellStatus productSellStatus) {
        return productSellStatus ==
                null ? null : QProduct.product.productSellStatus.eq(productSellStatus);
    }


    private BooleanExpression registerDateTimeAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d", searchDateType)) {
            dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w", searchDateType)) {
            dateTime = dateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m", searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m", searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        }
        return QProduct.product.regTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {

        if(StringUtils.equals("productName", searchBy)) {
            return QProduct.product.productName.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("createdBy", searchBy)) {
            return QProduct.product.createdBy.like("%" + searchQuery + "%");
        }
        return null;
    }



    @Override
    public Page<Product> getAdminProductPage(ProductSearchDTO productSearchDTO, Pageable pageable) {


        /**
         * queryFactory는 Querydsl 쿼리를 생성하는 객체입니다.
         * selectFrom(QProduct.product)는 Product 테이블에서 데이터를 선택한다는 의미입니다.
         */
        List<Product> content = queryFactory
                .selectFrom(QProduct.product)
                .where(
                        registerDateTimeAfter(productSearchDTO.getSearchDateType()),
                        searchSellStatusEqual(productSearchDTO.getSearchSellStatus()),
                        searchByLike(productSearchDTO.getSearchBy(), productSearchDTO.getSearchQuery())
                )
                .orderBy(QProduct.product.productId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        /**
         * 두 번째 쿼리는 총 항목 수를 계산합니다.
         * Wildcard.count는 SQL의 COUNT(*)와 같은 역할을 합니다.
         */
        Long countResult = queryFactory
                .select(Wildcard.count)
                .from(QProduct.product)
                .where(
                        registerDateTimeAfter(productSearchDTO.getSearchDateType()),
                        searchSellStatusEqual(productSearchDTO.getSearchSellStatus()),
                        searchByLike(productSearchDTO.getSearchBy(), productSearchDTO.getSearchQuery())
                )
                .fetchOne(); /// 단일 결과(카운트)를 가져옵니다.

        ///  만약 카운트 결과가 null 이면 0으로 설정합니다.
        long total = countResult != null ? countResult : 0L;

        /**
         * 최종적으로 PageImpl 객체를 생성하여 반환합니다:
         * content: 현재 페이지의 상품 목록
         * pageable: 페이지 정보
         * total: 전체 상품 수
         */
        return new PageImpl<>(content, pageable, total);
    }


}
