package com.bbmore.admin.anotice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@Data
public class NoticeDTO {

    private Integer noticeCode;
    private String noticeType;
    private String noticeTitle;
    private LocalDate noticeCreatedDate;
    private Integer noticeView;
    private String noticeContent;


}
