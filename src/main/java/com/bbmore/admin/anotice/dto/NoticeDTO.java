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

//    convertToDTO 때문에 추가함
    public NoticeDTO(Integer noticeCode, String noticeType, String noticeTitle, LocalDate noticeCreatedDate, Integer noticeView) {
        this.noticeCode = noticeCode;
        this.noticeType = noticeType;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeCreatedDate = noticeCreatedDate;
        this.noticeView = noticeView;
    }


}
