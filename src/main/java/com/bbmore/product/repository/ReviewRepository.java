package com.bbmore.product.repository;

import com.bbmore.product.dto.ReviewListDTO;
import com.bbmore.product.dto.ReviewWriteDTO;
import com.bbmore.product.dto.ReviewWritingDTO;
import com.bbmore.product.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    // 📌 [user_review_write] 리뷰 작성 가능한 상품만 조회 (리뷰가 아직 없는 주문 상세만)
    @Query("SELECT new com.bbmore.product.dto.ReviewWriteDTO" +
            "(o.orderDate, o.orderStatus, " +
            "p.productName, od.orderDetailCode, od.orderDetailPrice, od.orderDetailQuantity) " +
            "FROM Order o " +
            "JOIN o.member m " +
            "JOIN OrderDetail od ON o.orderCode = od.order.orderCode " +
            "JOIN Product p ON od.product.productCode = p.productCode " +
            "LEFT JOIN Review r ON r.orderDetail.orderDetailCode = od.orderDetailCode " +
            "WHERE m.userCode = :userCode AND r.reviewCode IS NULL")
    List<ReviewWriteDTO> findUserPurchaseList(@Param("userCode") Integer userCode);

    /*
     * 💡 LEFT JOIN Review로 연결 후, 리뷰가 존재하지 않는 (IS NULL) 항목만 필터링
     * => 리뷰를 작성하지 않은 주문 상세만 조회됨
     */

    // 📌 리뷰가 없는 경우 orderDetailCode로 상품 및 주문 정보만 가져오기
    @Query("SELECT new com.bbmore.product.dto.ReviewWritingDTO(" +
            "o.orderStatus, p.productName, od.orderDetailPrice, od.orderDetailQuantity) " +
            "FROM OrderDetail od " +
            "JOIN Order o ON od.order.orderCode = o.orderCode " +
            "JOIN Product p ON od.product.productCode = p.productCode " +
            "WHERE o.member.userCode = :userCode AND od.orderDetailCode = :orderDetailCode")
    ReviewWritingDTO findProductInfoForReview(@Param("userCode") Integer userCode, @Param("orderDetailCode") Integer orderDetailCode);




    // 📌 [user_review_writing] 수정 시 기존 리뷰 불러오기
    @Query("SELECT new com.bbmore.product.dto.ReviewWritingDTO" +
            "(r.reviewCode, r.reviewRating, r.reviewContent, o.orderStatus, " +
            "p.productName, od.orderDetailPrice, od.orderDetailQuantity) " +
            "FROM Review r " +
            "JOIN OrderDetail od ON r.orderDetail.orderDetailCode = od.orderDetailCode " +
            "JOIN Order o ON od.order.orderCode = o.orderCode " +
            "JOIN Product p ON od.product.productCode = p.productCode " +
            "WHERE r.member.userCode = :userCode AND od.orderDetailCode = :orderDetailCode")
    ReviewWritingDTO findReviewByUserAndOrderDetail(@Param("userCode") Integer userCode, @Param("orderDetailCode") Integer orderDetailCode);

    /*
     * 💡 리뷰가 이미 존재하는 경우 해당 리뷰와 상품 정보, 주문 상태 등을 가져옴
     * => 수정 폼에서 사용
     */

    // 📌 [user_review_list] 사용자가 작성한 모든 리뷰 목록 조회
    @Query("SELECT DISTINCT new com.bbmore.product.dto.ReviewListDTO" +
            "(r.reviewCode, od.orderDetailCode, o.orderStatus, p.productName," +
            "od.orderDetailPrice, od.orderDetailQuantity, r.reviewRating, r.reviewDate) " +
            "FROM Review r " +
            "JOIN OrderDetail od ON r.orderDetail.orderDetailCode = od.orderDetailCode " +
            "JOIN Order o ON od.order.orderCode = o.orderCode " +
            "JOIN Product p ON od.product.productCode = p.productCode " +
            "WHERE r.member.userCode = :userCode")
    List<ReviewListDTO> findUserReviewList(@Param("userCode") Integer userCode);

    /*
     * 💡 리뷰가 작성된 항목들만 가져오기 때문에 Review 기준으로 조회
     */

    // ✅ 추가된 메서드: 특정 주문 상세에 이미 작성된 리뷰가 있는지 확인
    Optional<Review> findByOrderDetail_OrderDetailCode(Integer orderDetailCode);




}
