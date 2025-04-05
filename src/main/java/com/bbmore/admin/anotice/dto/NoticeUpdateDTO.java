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

public class NoticeUpdateDTO {

  private Integer noticeCode;
  private String noticeType;
  private String noticeTitle;
  private LocalDate noticeCreatedDate;
  private Integer noticeView;
  private String noticeContent;

}
