package com.bbmore.admin.anotice.dto;

import lombok.*;
import java.time.LocalDate;

// 롬복이 말을 안들음 버전 다시 확인해볼 것. 조회는 됨

@NoArgsConstructor
@AllArgsConstructor //(@Getter, @Setter, @ToString)
@Getter
@Setter
@ToString
public class NoticeDTO {

    private Integer noticeCode;
    private String noticeType;
    private String noticeTitle;
    private LocalDate noticeCreatedDate;
    private Integer noticeView;
    private String noticeContent;


}
