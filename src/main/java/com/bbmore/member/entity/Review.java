package com.bbmore.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_code")
    private Integer reviewCode;

    @Column(name = "review_rating", nullable = false)
    private Integer reviewRating;

    @Column(name = "review_content", nullable = false)
    private String reviewContent;

    @Column(name = "review_date")
    private LocalDate reviewDate;

    // FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code", nullable = false)
    private Member member;

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