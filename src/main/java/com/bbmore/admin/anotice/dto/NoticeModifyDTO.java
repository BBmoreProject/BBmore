package com.bbmore.admin.anotice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor //(@Getter, @Setter, @ToString)
@Getter
@Setter
@ToString
// 공지사항 등록 DTO
public class NoticeModifyDTO {


    private String noticeTitle;
    private String noticeContent;



}
