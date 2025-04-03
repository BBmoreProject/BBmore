package com.bbmore.product.repository;

import com.bbmore.product.constant.ProductSellStatus;
import com.bbmore.product.dto.MainProductDTO;
import com.bbmore.product.dto.ProductSearchDTO;
import com.bbmore.product.dto.QMainProductDTO;
import com.bbmore.product.entity.Product;
import com.bbmore.product.entity.QProduct;
import com.bbmore.product.entity.QProductImg;
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

import static com.bbmore.product.entity.QProductImg.productImg;
import static com.querydsl.core.types.ExpressionUtils.orderBy;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory queryFactory;

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
                .orderBy(QProduct.product.id.desc())
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

    ///  사용자가 입력한 검색어를 받아
    /// /// 이 검색어가 어디든 표시된 모든 제품을 찾아줘! % searchQuery %
    /// null 이거나 빈 문자열 일땐 StringUtils.isEmpty(searchQuery) 이므로 null 을 반환합니다.
    private BooleanExpression productNameLike(String searchQuery) {

        return StringUtils.isEmpty(searchQuery)
                ? null : QProduct.product.productName.like("%" + searchQuery + "%");

    }

    @Override
    ///  검색 조건을 담는 DTO 와 페이징 정보
    public Page<MainProductDTO> getMainProductPage(ProductSearchDTO productSearchDTO, Pageable pageable) {
        QProduct product = QProduct.product; /// Q타입 초기화
        QProductImg productImg = QProductImg.productImg;

        ///  Q 타입 객체 초기화, 테이블에 매핑되는 엔티티의 메타 정보를 담고 있음
        List<MainProductDTO> content = queryFactory ///  쿼리를 시작하고 결과를 content List에 담음
                .select( ///  쿼리 결과로 반환할 필드를 지정
                        ///  productName, productDetail, productImgUrl, productPrice)을 담은 DTO 객체를 생성
                        new QMainProductDTO(
                                product.id,
                                product.productName,
                                product.productDetail,
                                productImg.imgUrl,
                                product.price)
                ) ///  Querydsl 프로젝션 기능을 사용한 것으로, 쿼리 결과를 바로 DTO 변환
                .from(productImg) ///  기본 테이블을 productImg로 설정
                .join(productImg.product, product) ///  productImg, product 테이블 조인(productImg.product는 productImg엔티티의 product타입필드)
                .where(productImg.representativeImg.eq("Y")) /// 대표 이미지만 필터링
                .where(productNameLike(productSearchDTO.getSearchQuery())) ///  검색어가 포함된 레코드만 필터링
                .orderBy(product.id.desc()) ///  내림차순 정리
                .offset(pageable.getOffset()) /// 페이징, offset: 건너 뛸 레코드, limit: 최대 레코드 수
                .limit(pageable.getPageSize())
                .fetch(); /// 쿼리 실행 후 리스트 반환

        long total = queryFactory ///  전체 레코드 수를 계산하는 두 번째 쿼리
                .select(Wildcard.count) ///  Wildcard.count는 COUNT(*) SQL 문을 생성
                .from(productImg)
                .join(productImg.product, product)
                .where(productImg.representativeImg.eq("Y"))
                .where(productNameLike(productSearchDTO.getSearchQuery()))
                .fetchOne(); /// 단일 결과 반환 (카운트 쿼리)


        ///  Page 인터페이스 객체 생성
        return new PageImpl<>(content, pageable, total);
    }


}
