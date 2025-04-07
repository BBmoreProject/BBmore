package com.bbmore.member.entity;

import com.bbmore.order.entity.OrderDetail;
import jakarta.persistence.*;
import lombok.*;

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


}