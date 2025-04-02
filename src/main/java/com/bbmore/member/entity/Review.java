package com.bbmore.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_code")
    private int reviewCode;

    @Column(name = "review_rating", nullable = false)
    private int reviewRating;

    @Column(name = "review_content", nullable = false)
    private String reviewContent;

    @Column(name = "review_date")
    private Date reviewDate;

    // FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)  // 수정 필요 (?)
    @JoinColumn(name = "order_detail_code", nullable = false)
    private OrderDetail orderDetail;

}