package com.bbmore.admin.anotice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor(access = AccessLevel.PROTECTED)  /*생성자가 없어도 인스턴스 생성 가능 */
@Getter
@Entity
@Table(name = "tbl_notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int noticeCode;

    @Column(nullable = false, length = 255)
    private String noticeType;

    @Column(nullable = false, length = 255)
    private String noticeTitle;

    @Column(nullable = false)
    private Date noticeCreatedDate;

//    조회수
    @Column(nullable = false)
    private int noticeView;

    @Column(nullable = false, length = 255)
    private String noticeContent;

    public void modifyNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public void modifyNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }




}
