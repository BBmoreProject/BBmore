package com.bbmore.admin.anotice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor //(@Getter, @Setter, @ToString)
@Getter
@Setter
@ToString
// 공지사항 수정 DTO
public class NoticeEditDTO {


    private Integer noticeCode;
    private String noticeType;
    private String noticeTitle;     // 제목 수정
    private String noticeContent;   // 내용 수정
    private Integer noticeView;     // 조회수 수정

}
