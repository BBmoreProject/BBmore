package com.bbmore.product.entity;

import com.bbmore.member.entity.Member;
import com.bbmore.order.entity.OrderDetail;
import jakarta.persistence.*;
import lombok.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Table(name = "tbl_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_code")
    private Integer reviewCode;             // 리뷰고유번호

    @Column(name = "review_rating", nullable = false)
    private Integer reviewRating;           // 리뷰별점

    @Column(name = "review_date",  nullable = false)
    private LocalDate reviewDate;           // 리뷰작성일

    @Column(name = "review_content")
    private String reviewContent;           // 리뷰내용


    // fk(회원고유번호)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code", nullable = false)
    private Member member;

    // fk(주문상세고유번호)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_code", nullable = false)
    private OrderDetail orderDetail;

    @Builder
    public Review(Integer reviewCode, Integer reviewRating, String reviewContent, LocalDate reviewDate, Member member, OrderDetail orderDetail) {
        this.reviewCode = reviewCode;
        this.reviewRating = reviewRating;
        this.reviewContent = reviewContent;
        this.reviewDate = reviewDate;
        this.member = member;
        this.orderDetail = orderDetail;
    }

    // 리뷰 수정 메서드 추가 (Setter 대신)
    public void updateReview(Integer reviewRating, String reviewContent) {
        this.reviewRating = reviewRating;
        this.reviewContent = reviewContent;
    }

    public void assignMemberAndOrderDetail(Member member, OrderDetail orderDetail) {
        this.member = member;
        this.orderDetail = orderDetail;
    }

    @PrePersist
    protected void onCreate() {
        if (this.reviewDate == null) {
            this.reviewDate = LocalDate.now();
        }
    }




}