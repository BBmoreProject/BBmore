package com.bbmore.admin.anotice.dto;

import lombok.*;
import java.time.LocalDate;

// 롬복이 말을 안들음 버전 다시 확인해볼 것. 조회는 됨

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDTO {

    private Integer noticeCode;              // 게시글고유번호

    private String noticeType;               // 게시글타입

    private String noticeTitle;               // 게시글제목

    private LocalDate noticeCreatedDate;     // 게시글작성일

    private Integer noticeView;               // 게시글 조회수

    private String noticeContent;            // 게시글 내용


}
