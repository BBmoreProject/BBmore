package com.bbmore.admin.anotice.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor //(@Getter, @Setter, @ToString)
@Getter
@Setter
@ToString
public class NoticeViewDTO {

  private Integer noticeCode;
  private String noticeType;
  private String noticeTitle;
  private LocalDate noticeCreatedDate;
  private Integer noticeView;
  private String noticeContent;



}
