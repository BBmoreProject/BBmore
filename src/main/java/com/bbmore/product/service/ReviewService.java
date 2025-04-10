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

        // userCode와 orderDetailCode를 DTO에 직접 세팅
        review.setUserCode(userCode);
        review.setOrderDetailCode(orderDetailCode);

        return review;
    }


    // 리뷰 저장 (등록)
    @Transactional
    public void saveReview(ReviewWritingDTO reviewDTO) {


        // 이미 같은 orderDetail 에 리뷰가 있는지 확인
        Optional<Review> existingReview = reviewRepository.findByOrderDetail_OrderDetailCode(reviewDTO.getOrderDetailCode());
        if (existingReview.isPresent()) {
            throw new IllegalStateException("이미 리뷰가 작성된 주문입니다.");
        }


        Member member = memberRepository.findById(reviewDTO.getUserCode())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        OrderDetail orderDetail = orderDetailRepository.findById(reviewDTO.getOrderDetailCode())
                .orElseThrow(() -> new IllegalArgumentException("해당 주문 상세가 존재하지 않습니다."));

        Review review = reviewDTO.toEntity(member, orderDetail);

        reviewRepository.save(review);
    }


    // 리뷰 수정
    @Transactional
    public void updateReview(ReviewWritingDTO reviewDTO) {
        System.out.println("수정 요청된 리뷰 코드: " + reviewDTO.getReviewCode());

        Review review = reviewRepository.findById(reviewDTO.getReviewCode())
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다."));
        review.updateReview(reviewDTO.getReviewRating(), reviewDTO.getReviewContent());
    }



    // user_review_list
    public List<ReviewListDTO> getUserReviewList(Integer userCode) {
        return reviewRepository.findUserReviewList(userCode);

    }

    // 리뷰 삭제
    @Transactional
    public void deleteReview(Integer reviewCode) {
        System.out.println("삭제 요청 도착! reviewCode = " + reviewCode);
        reviewRepository.deleteById(reviewCode);
    }


}
