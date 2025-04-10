package com.bbmore.notice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserNoticeDTO {

  private Integer noticeCode;
  private String noticeType;
  private String noticeTitle;
  private LocalDate noticeCreatedDate;
  private Integer noticeView;
  private String noticeContent;

}
