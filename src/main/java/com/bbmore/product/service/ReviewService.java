package com.bbmore.product.service;

import com.bbmore.product.dto.ReviewListDTO;
import com.bbmore.product.dto.ReviewWriteDTO;
import com.bbmore.product.dto.ReviewWritingDTO;
import com.bbmore.member.entity.Member;
import com.bbmore.order.entity.OrderDetail;
import com.bbmore.product.entity.Review;
import com.bbmore.member.repository.MemberRepository;
import com.bbmore.order.repository.OrderDetailRepository;
import com.bbmore.product.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final OrderDetailRepository orderDetailRepository;

    // user_review_write
    // 구매한 목록 조회
    public List<ReviewWriteDTO> getUserPurchaseList(Integer userCode) {
        return reviewRepository.findUserPurchaseList(userCode);
    }

    // user_review_writing
    // 특정 주문 상세에 대한 리뷰 정보 조회
    public ReviewWritingDTO getReviewInfo(Integer userCode, Integer orderDetailCode) {
        ReviewWritingDTO review = reviewRepository.findReviewByUserAndOrderDetail(userCode, orderDetailCode);

        if (review == null) {
            // 리뷰가 없는 경우 상품 정보만 가져오기
            review = reviewRepository.findProductInfoForReview(userCode, orderDetailCode);
        }

        // ⭐️ userCode와 orderDetailCode를 DTO에 직접 세팅
        review.setUserCode(userCode);
        review.setOrderDetailCode(orderDetailCode);

        return review;
    }


    /*
    * - 각 주문상세(orderDetail)는 하나의 리뷰(review)만 가질수있음. -> 단일 객체 반환
    * - findReviewByUserAndOrderDetail 은 ReviewWritingDTO 한개만 반환하도록 수정
    * - getReviewInfo 의 반환 타입도 ReviewWritingDTO 로 변경
    * */


    // 리뷰 저장 (등록)
    @Transactional
    public void saveReview(ReviewWritingDTO reviewDTO) {


        // ✅ 이미 같은 orderDetail에 리뷰가 있는지 확인
        Optional<Review> existingReview = reviewRepository.findByOrderDetail_OrderDetailCode(reviewDTO.getOrderDetailCode());
        if (existingReview.isPresent()) {
            throw new IllegalStateException("이미 리뷰가 작성된 주문입니다.");
        }


        Member member = memberRepository.findById(reviewDTO.getUserCode())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        OrderDetail orderDetail = orderDetailRepository.findById(reviewDTO.getOrderDetailCode())
                .orElseThrow(() -> new IllegalArgumentException("해당 주문 상세가 존재하지 않습니다."));

        Review review = reviewDTO.toEntity(member, orderDetail);

//        review.assignMemberAndOrderDetail(member, orderDetail);

        reviewRepository.save(review);
    }
    /*
    * ReviewWritingDTO(DTO) -> Review(Entity) 변환
    * JpaRepository 의 save 호출 => SQL INSERT 문 실행
    * ==> 신규 객체이면 INSERT, 기존 객체라면 UPDATE 수행
    * */

    // 리뷰 수정
    @Transactional
    public void updateReview(ReviewWritingDTO reviewDTO) {
        System.out.println("수정 요청된 리뷰 코드: " + reviewDTO.getReviewCode()); // 확인용

        Review review = reviewRepository.findById(reviewDTO.getReviewCode())
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다."));
        review.updateReview(reviewDTO.getReviewRating(), reviewDTO.getReviewContent());
    }
    /*
    * findById(reviewDTO.getReviewCode()) => reviewCode 로 리뷰 조회 (없으면 예외처리)
    * review.updateReview(...) =? Review 엔티티에 정의된 updateReview(커스텀 메서드)를 호출해서 필드 값을 변경
    * => 이 변경이 자동으로 DB 에 반영됨
    *
    * 💡 왜 save()를 안 써도 될까?
    * JPA 는 **영속성 컨텍스트(Persistence Context)**를 사용해서, @Transactional 안에서 조회한 엔티티의 값이 변경되면 자동으로 UPDATE가 수행됨(더티 체킹, Dirty Checking).
    * */


    // user_review_list
    public List<ReviewListDTO> getUserReviewList(Integer userCode) {
        return reviewRepository.findUserReviewList(userCode);

    }

    // 리뷰 삭제
    @Transactional
    public void deleteReview(Integer reviewCode) {
        System.out.println("🔥 삭제 요청 도착! reviewCode = " + reviewCode);
        reviewRepository.deleteById(reviewCode);
    }
    /*
    * deleteById(reviewCode);
    * => JPA가 reviewCode를 기준으로 해당 리뷰를 DELETE.
    * => 내부적으로 SQL DELETE FROM tbl_review WHERE review_code = ? 실행됨.
    * */


}
