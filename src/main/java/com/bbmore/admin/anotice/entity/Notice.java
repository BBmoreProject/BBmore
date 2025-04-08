package com.bbmore.admin.anotice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Table(name = "tbl_notice")
public class Notice {
    @Id // jpa에서는 jakarta.persistence.* 선택할 것
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeCode;     // 게시글 고유번호

    @Column(name = "notice_type", nullable = false)
    private String noticeType;      // 게시글 타입(공지사항, 자주묻는질문)

    @Column(name = "notice_title", nullable = false)
    private String noticeTitle;      // 게시글 제목

    @Column(name = "notice_created_date", nullable = false, updatable = false)
    private LocalDate noticeCreatedDate;    // 등록일자

  
    @Column(name = "notice_view" , columnDefinition = "Integer default 0", nullable = false)
    private Integer noticeView;             // 조회수 

    public void setNoticeView(Integer noticeView) {  // Setter 추가
        this.noticeView = noticeView;
    }


    @Column(name = "notice_content", nullable = false)
    private String noticeContent;           // 게시글 내용



    // 엔티티가 DB에 저장되기 전에 자동으로 등록일자를 설정하는 메서드
    @PrePersist
    public void onPrePersist() {
        if (noticeCreatedDate == null) {
            noticeCreatedDate = LocalDate.now();  // 엔티티가 DB에 저장되기 전에 현재 날짜와 시간 설정
        }
        if (noticeView == null) {  // 조회수가 null이면 기본값 0 설정
            noticeView = 0;
        }

    }

}