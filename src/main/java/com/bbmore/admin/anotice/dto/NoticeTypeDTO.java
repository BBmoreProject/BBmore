package com.bbmore.admin.anotice.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor //(@Getter, @Setter, @ToString)
@Getter
@Setter
@ToString
// 공지사항 페이지에 공지사항 찾아올때 사용하는 DTO
public class NoticeTypeDTO {

  private Integer noticeCode;
  private String noticeType;
  private String noticeTitle;
  private LocalDate noticeCreatedDate;
  private Integer noticeView;
  private String noticeContent;

}
