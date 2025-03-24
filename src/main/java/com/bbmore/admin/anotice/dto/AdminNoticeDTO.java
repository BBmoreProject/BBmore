package com.bbmore.admin.anotice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class AdminNoticeDTO {

    private int notice_code;
    private String notice_type;
    private String notice_title;
    private Date notice_created_date;
    private int notice_view;
    private String notice_content;

    public AdminNoticeDTO() {
    }

}
