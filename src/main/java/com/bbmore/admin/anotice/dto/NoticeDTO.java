package com.bbmore.admin.anotice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class NoticeDTO {

    private int noticeCode;
    private String noticeType;
    private String noticeTitle;
    private Date noticeCreatedDate;
    private int noticeView;
    private String noticeContent;


}
