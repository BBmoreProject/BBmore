package com.bbmore.admin.anotice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor(access = AccessLevel.PROTECTED)  /*생성자가 없어도 인스턴스 생성 가능 */
@Getter
@Entity
@Table(name = "tbl_notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeCode;

    @Column(name = "notice_type", nullable = false, length = 255)
    private String noticeType;

    @Column(name = "notice_title", nullable = false, length = 255)
    private String noticeTitle;

    @Column(name = "notice_created_date", nullable = false, updatable = false)
    private LocalDate noticeCreatedDate;    // 등록일자

    //    조회수
    @Column(name = "notice_view" , columnDefinition = "Integer default 0", nullable = false)
    private Integer noticeView;

    public void setNoticeView(Integer noticeView) {  // Setter 추가
        this.noticeView = noticeView;
    }


    @Column(nullable = false, length = 255)
    private String noticeContent;


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



    // 수정 - 공지사항 제목 수정
    public void modifyNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    // 수정 - 공지사항 내용 수정
    public void modifyNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }





}
